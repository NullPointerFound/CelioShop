import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { ProductService } from "../../services/ProductService";
import { ServiceCart } from "../../services/CartService";
import axios from "axios";
import { useCartContext } from "../../contexts/CartContext/CartContext";
import { useUserContext } from "../../contexts/UserContext/UserContext";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { AiFillStar } from "react-icons/ai";

const ProductItem = ({ selectedCategory }) => {
  const [products, setProducts] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [add, setAdd] = useState(false);
  const [cart, setCart] = useCartContext();
  const [user, setUser] = useUserContext();

  useEffect(() => {
    setLoading(true);
    const getAllProducts = async () => {
      await ProductService.Get_All_Products(null)
        .then((res) => {
          setProducts(res);
          setError(null);
          setLoading(false);
        })
        .catch((e) => {
          setError(e.message);
          setLoading(false);
        });
    };

    const getProductsByCategory = async () => {
      await ProductService.Get_Products_By_Category(selectedCategory)
        .then((res) => {
          setProducts(res);
          setError(null);
          setLoading(false);
        })
        .catch((e) => {
          setError(e.message);
          setLoading(false);
        });
    };

    if (!selectedCategory) {
      getAllProducts();
    } else {
      getProductsByCategory();
    }
  }, [selectedCategory]);

  const addToCartHandler = async (id, quantity) => {
    if (user?.status === "completed" && user?.user?.accessToken) {
      await axios
        .post(
          `${process.env.REACT_APP_API_URL}/cart/${id}/${quantity}`,
          {
            productId: id,
            quantity: quantity,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${user?.user.accessToken}`,
            },
          }
        )
        .then(() => {
          setAdd(true);
          toast.success("Item added to cart!");
        });
    } else {
      toast.error("You need to be logged in to perform this action");
    }
  };

  useEffect(() => {
    const getCartItems = async () => {
      await ServiceCart.Get_Cart(null, user?.user.accessToken).then((res) => {
        setAdd(false);
        setCart(res);
      });
    };
    if (add) getCartItems();
  }, [add]);

  return (
    <Wrapper>
      <div className="products">
        {products?.map((product) => {
          return (
            <div className="card" key={product.id}>
              <div key={product.id} className="card-body">
                <Link to={`/product/${product?.id}`}>
                  <img src={product.imgUrl} alt={product.name} />
                </Link>
                <div className="row">
                  <Link to={`/product/${product?.id}`}>
                    <div className="card-title">
                      <h4>{product.name}</h4>
                      <div
                        style={{
                          display: "flex",
                          alignItems: "center",
                          justifyContent: "space-between",
                          marginBottom: "8px",
                          flexDirection: "row-reverse",
                        }}
                      >
                        <div
                          style={{
                            display: "flex",
                            justifyContent: "space-between",
                            alignItems: "center",
                            columnGap: "6px",
                            color: "#4FAF61",
                            fontSize: "22px",
                          }}
                        >
                          <div>
                            {product.rateCount ? (
                              <>{`(${product.rateCount})`}</>
                            ) : null}
                          </div>
                          <div>{product.avgRate ? product.avgRate : "--"}</div>
                          <AiFillStar size={24} />
                        </div>

                        <div style={{ fontWeight: 600, fontSize: "22px" }}>
                          {product.quantity > 0
                            ? product.quantity + " left"
                            : null}
                        </div>
                      </div>
                      <div>
                        <h3 style={{ color: "#525E74" }}>${product.price}</h3>
                      </div>
                    </div>
                  </Link>

                  <div className="bottom">
                    <p>{product.description}</p>
                    <div className="btn-group">
                      {product.quantity > 0 ? (
                        <div
                          onClick={() => addToCartHandler(product.id, 1)}
                          className="btn"
                        >
                          <span> Add to cart</span>
                        </div>
                      ) : (
                        <div className="out-of-stock">Out of stock</div>
                      )}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          );
        })}
      </div>
      <ToastContainer />
    </Wrapper>
  );
};

const Wrapper = styled.article`
  .products {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    align-items: stretch;
    grid-auto-rows: 1fr;
    gap: 32px;
    padding: 32px;
  }

  .row {
    display: flex;
    flex-direction: column;
    flex: 1;
  }

  .card {
    border-radius: 5px;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
  }

  .card > *:not(img) {
    padding: 16px 10px;
  }

  .card img {
    width: 100%;
    object-fit: cover;
    margin-bottom: 16px;
  }

  .card-body {
    padding: 5px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
  }

  .card-body p {
    color: #3d3d3d;
    margin-bottom: 20px;
    font-size: 14px;
  }

  .view-btn a {
    padding: 5px 15px;
    border: 1.5px solid #007bff;
    border-radius: 3px;
    text-decoration: none;
    color: #007bff;
  }

  .btn-group {
    display: flex;
    margin-top: auto;
    column-gap: 32px;
    align-items: end;
    justify-content: center;
    flex: 1;
  }

  .btn-group .btn {
    padding: 5px 15px;
    background-color: #4faf61;
    color: #fff;
    border-radius: 3px;
    margin-left: -2px;
  }

  .btn-group a {
    margin: 0 10px;
    text-decoration: none;
    color: #000;
  }

  .card-title {
    align-self: baseline;
  }
  .card-title h4 {
    font-size: 18px;
  }

  .card-title h3 {
    font-size: 20px;
  }

  .bottom {
    margin-top: auto;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex: 1;
  }

  @media (max-width: 1024px) {
    .products {
      grid-template-columns: 1fr 1fr;
    }
  }

  @media (max-width: 700px) {
    .products {
      grid-template-columns: 1fr;
    }
  }
`;
export default ProductItem;
