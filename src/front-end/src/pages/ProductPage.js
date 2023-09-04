import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Navbar from "../components/Navbar/Navbar";
import Footer from "../components/Footer/Footer";
import { ProductService } from "../services/ProductService";
import { useParams } from "react-router-dom";
//import { Add, Remove } from '@material-ui/icons';
import {
  AiFillPlusCircle,
  AiFillStar,
  AiOutlineMinus,
  AiOutlinePlus,
  AiOutlinePlusCircle,
} from "react-icons/ai";
import { useCartContext } from "../contexts/CartContext/CartContext";
import { ServiceCart } from "../services/CartService";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useUserContext } from "../contexts/UserContext/UserContext";
import { TextField } from "@mui/material";
import { StarBorderOutlined, StarRate } from "@mui/icons-material";

const ProductPage = () => {
  const [quantity, setQuantity] = useState(1);
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [add, setAdd] = useState(false);
  const [cart, setCart] = useCartContext();
  const [user, setUser] = useUserContext();
  const [reviews, setReviews] = useState(null);
  const [notFound, setnotFound] = useState(null);
  const [headline, setHeadline] = useState("");
  const [comment, setComment] = useState("");
  const [rate, setRate] = useState(null);
  const [refresh, setRefresh] = useState(true);

  const { id } = useParams();

  const handleQty = (type) => {
    if (type === "dec") {
      quantity > 1 && setQuantity(quantity - 1);
    } else {
      quantity < product.quantity && setQuantity(quantity + 1);
    }
  };

  useEffect(() => {
    const getAllProducts = async () => {
      await ProductService.Get_Single_Product(
        id,
        "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuaiIsImlhdCI6MTY4MTYxODk4OSwiZXhwIjoxNjgyMjIzNzg5fQ.c9ysMKy9TlFbbsUdM8itSXfor20A8bOOUocY7CjqiA79YwVnFr_rHSGXHTBTTV6I"
      )
        .then((res) => {
          setProduct(res);
          setError(null);
          setLoading(false);
        })
        .catch((e) => {
          setError(e.message);
          if (e?.response?.status === 404) {
            window.location.replace("/error");
          }
          setLoading(false);
        });
    };

    getAllProducts();
  }, []);

  const addToCart = async () => {
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
              Authorization: `Bearer ${user?.user?.accessToken}`,
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
      await ServiceCart.Get_Cart(user?.user?.accessToken).then((res) => {
        setAdd(false);
        setCart(res);
      });
    };
    if (add) getCartItems();
  }, [add]);

  const submitReview = async () => {
    await axios
      .post(
        `${process.env.REACT_APP_API_URL}/product/${id}/review`,
        {
          headline: headline,
          comment: comment,
          rate: rate,
          id: id,
        },

        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${user?.user?.accessToken}`,
          },
        }
      )
      .then(() => {
        setRefresh(true);
        toast.success("Your review has been submitted ");
      });
  };

  useEffect(() => {
    const getReviews = async () => {
      await axios
        .get(`${process.env.REACT_APP_API_URL}/product/${id}/reviews`)
        .then((res) => {
          setReviews(res.data);
          setRefresh(false);
        });
    };
    if (refresh) getReviews();
  }, [user, refresh]);
  return (
    <Container>
      <ToastContainer />
      <Navbar />
      {product ? (
        <Wrapper>
          <Left>
            <Image src={product?.imgUrl} />
          </Left>

          <Right>
            <ProductName>{product?.name}</ProductName>

            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                columnGap: "6px",
              }}
            >
              <Price>${product?.price}</Price>
              {product?.quantity > 0 ? (
                <Description>({product.quantity}) left</Description>
              ) : (
                <div className="out-of-stock">Out of stock</div>
              )}
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  columnGap: "6px",
                  color: "#4FAF61",
                  fontSize: "26",
                }}
              >
                <div>{product?.avgRate ? product.avgRate : "--"}</div>
                <AiFillStar size={24} />
              </div>
            </div>
            <Description>{product?.description}</Description>
            {product?.quantity > 0 && (
              <AddContainer>
                <AmountContainer>
                  <AiOutlineMinus
                    size={26}
                    onClick={() => handleQty("dec")}
                    style={{ cursor: "pointer" }}
                  />
                  <Amount>{quantity}</Amount>
                  <AiOutlinePlus
                    size={26}
                    onClick={() => handleQty("inc")}
                    style={{ cursor: "pointer" }}
                  />
                </AmountContainer>
                <div class="btn-group">
                  <div onClick={addToCart} className="btn">
                    <span> Add to cart</span>
                  </div>
                </div>
              </AddContainer>
            )}
          </Right>
          <div className="top" style={{ gridColumn: "span 2" }}>
            {user?.user?.accessToken ? (
              <div className="review-inputs">
                <div
                  className="review-inputs-top"
                  style={{
                    display: "flex",
                    alignItems: "center",
                    columnGap: "24px",
                  }}
                >
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="headline"
                    label="Headline"
                    name="headline"
                    autoComplete="headline"
                    autoFocus
                    value={headline}
                    onChange={(e) => setHeadline(e.target.value)}
                  />
                  <div
                    className="stars"
                    style={{
                      display: "flex",
                      alignItems: "center",
                    }}
                  >
                    <div>
                      {rate ? (
                        <div onClick={() => setRate(1)}>
                          <StarRate />
                        </div>
                      ) : (
                        <div onClick={() => setRate(1)}>
                          <StarBorderOutlined />
                        </div>
                      )}
                    </div>

                    <div>
                      {rate >= 2 ? (
                        <div onClick={() => setRate(2)}>
                          <StarRate />
                        </div>
                      ) : (
                        <div onClick={() => setRate(2)}>
                          <StarBorderOutlined />
                        </div>
                      )}
                    </div>

                    <div>
                      {rate >= 3 ? (
                        <div onClick={() => setRate(3)}>
                          <StarRate />
                        </div>
                      ) : (
                        <div onClick={() => setRate(3)}>
                          <StarBorderOutlined />
                        </div>
                      )}
                    </div>

                    <div onClick={() => setRate(4)}>
                      {rate >= 4 ? (
                        <div>
                          <StarRate />
                        </div>
                      ) : (
                        <div onClick={() => setRate(4)}>
                          <StarBorderOutlined />
                        </div>
                      )}
                    </div>

                    <div>
                      {rate === 5 ? (
                        <div onClick={() => setRate(5)}>
                          <StarRate />
                        </div>
                      ) : (
                        <div onClick={() => setRate(5)}>
                          <StarBorderOutlined />
                        </div>
                      )}
                    </div>
                  </div>
                </div>

                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    columnGap: "24px",
                    marginBottom: "24px",
                  }}
                  className="review-inputs-top"
                >
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="comment"
                    label="Comment"
                    name="comment"
                    autoComplete="comment"
                    autoFocus
                    value={comment}
                    onChange={(e) => setComment(e.target.value)}
                  />

                  <div className="btn-group">
                    <div onClick={submitReview} className="btn">
                      <span> Submit</span>
                    </div>
                  </div>
                </div>
              </div>
            ) : null}

            {reviews?.length ? (
              <div className="reviews">
                {reviews?.map((review) => (
                  <div key={review.id} className="review">
                    <div style={{ fontWeight: "600", fontSize: "1.5rem" }}>
                      {review.user.username}
                    </div>
                    <div className="headline">
                      <div style={{ fontWeight: "600" }}>{review.headline}</div>
                      <div className="headline">
                        {review.rate ? review.rate : "--"}
                        <AiFillStar size={24} />
                      </div>
                    </div>

                    <div>{review.comment}</div>
                  </div>
                ))}
              </div>
            ) : (
              <div className="review">
                <div className="no-review">Be the first to give a review</div>
              </div>
            )}
          </div>
        </Wrapper>
      ) : null}

      <Footer />
    </Container>
  );
};

const Container = styled.div``;

const Wrapper = styled.div`
  padding: 60px 120px 120px 120px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  justify-content: space-between;

  .btn-group {
    display: flex;
    column-gap: 32px;
    align-items: end;
  }

  .stars div {
    cursor: pointer;
  }

  .review-inputs-top {
    width: 60%;
  }

  .top {
    margin: 24px 0;
  }

  .no-review {
    text-align: center;
    font-weight: 600;
    font-size: 18px;
  }

  @media (max-width: 800px) {
    .review-inputs-top {
      flex-direction: column !important;
      row-gap: 30px;
      width: 100%;
    }
  }

  .btn-group .btn {
    padding: 5px 15px;
    background-color: #4faf61;
    color: #fff;
    border-radius: 3px;
    margin-left: -2px;
  }

  .review {
    padding: 32px;
    border-bottom: 1px solid #ccc;
    background-color: #fff;
  }

  .headline {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  @media screen and (min-width: 320px) and (max-width: 1024px) {
    grid-template-columns: 1fr;
    display: flex;
    flex-direction: column;
    row-gap: 50px;
    padding: 60px 20px;
  }
`;

const Left = styled.div`
  display: grid;
`;
const Image = styled.img`
  border: solid 1px #eeeeee;
  width: 300px;
  object-fit: cover;

  @media screen and (min-width: 320px) and (max-width: 1024px) {
    width: 300px;
    display: flex;
    justify-self: center;
  }
`;

const Right = styled.div`
  display: flex;
  flex-direction: column;
  row-gap: 30px;
`;
const ProductName = styled.div`
  width: 100%;
  font-size: 48px;
  text-transform: uppercase;
  color: #000;
  border-bottom: solid 0.5px #eeeeee;
  padding-bottom: 10px;
  text-transform: capitalize;
`;
const Price = styled.span`
  font-size: 24px;
  text-transform: uppercase;
  color: #2b6431;
  padding-bottom: 10px;
  border-bottom: solid 3px #2b6431;
`;
const Description = styled.div`
  font-size: 24px;
  line-height: 30px;
`;

const AddContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;
const AmountContainer = styled.div`
  display: flex;
  align-items: center;
  font-weight: 500;
`;
const Amount = styled.span`
  width: 40px;
  height: 40px;
  font-size: 24px;
  border-radius: 10px;
  border: 1px solid #2b6431;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 10px;
`;
export default ProductPage;
