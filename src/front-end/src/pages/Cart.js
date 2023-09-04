import React, { useEffect, useState } from "react";
import { ServiceCart } from "../services/CartService";
import Navbar from "../components/Navbar/Navbar";
import Footer from "../components/Footer/Footer";
import styled from "styled-components";
import {
  AiOutlineMinus,
  AiOutlinePlus,
  AiOutlinePlusCircle,
} from "react-icons/ai";
import axios from "axios";
import { Delete } from "@mui/icons-material";
import { useCartContext } from "../contexts/CartContext/CartContext";
import { useUserContext } from "../contexts/UserContext/UserContext";
import { Box, TextField } from "@mui/material";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const [items, setItems] = useState(null);
  const [loading, setLoading] = useState(null);
  const [error, setError] = useState(null);
  const [fetch, setFetch] = useState(false);
  const [cart, setCart] = useCartContext();
  const [user, setUser] = useUserContext();
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");

  useEffect(() => {
    if (user?.status === "completed" && !user?.user?.accessToken) {
      window.location.replace("/");
    }
  }, [user]);
  useEffect(() => {
    const getCartItems = async () => {
      await ServiceCart.Get_Cart(null,user?.user?.accessToken)
        .then((res) => {
          setFetch(false);
          setCart(res);
          setLoading(false);
          setError(null);
        })
        .catch((e) => {
          setFetch(false);
          setError(e.message);
          setLoading(false);
        });
    };
    if (fetch) getCartItems();

    console.log(user);
  }, [fetch]);

  const navigate = useNavigate()

  const updateCart = async (id, quantity, stock, deleteFromCard, action) => {
    if (
      (quantity > 0 && (quantity <= stock || action === "minus")) ||
      deleteFromCard
    ) {
      await axios
        .put(
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
          setFetch(true);
        });
    }
  };

  const chekOutHandler = async () => {
    if (user?.status === "completed" && user?.user?.accessToken) {
      axios
        .post(
          `${process.env.REACT_APP_API_URL}/order/checkout`,
          {
            address: address,
            phoneNumber: phone,
            cart,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${user?.user?.accessToken}`,
            },
          }
        )
        .then((res) => {
          toast.success(res.data);
          setCart(null)
          navigate('/history')
        })
        .catch((err) => {
          console.log(err);
          toast.error(err.response.data.message);
        });
    }
  };
  return (
    <div>
      <Navbar />
      <ToastContainer />
      {user?.user?.accessToken && user?.status === "completed" ? (
        <>
          {cart?.items?.length ? (
            <Wrapper>
              <Box
                component="form"
                onSubmit={chekOutHandler}
                className="address"
              >
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="address"
                  label="Address"
                  name="address"
                  autoComplete="address"
                  autoFocus
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                />

                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="phone"
                  label="Phone"
                  name="phone"
                  autoComplete="phone"
                  autoFocus
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />

                <h4 style={{ marginBottom: 0 }}>${cart?.totalPrice}</h4>

                <div role="button" onClick={chekOutHandler} class="btn">
                  <span>Checkout</span>
                </div>
              </Box>
              <div className="shopping-cart">
                <div className="title">Shopping Bag</div>

                {cart?.items?.map((item) => (
                  <div key={item.id} className="item">
                    <div className="image">
                      <img src={item.product.imgUrl} alt={item.product.name} />
                    </div>

                    <div className="description">
                      <span>{item.product.name}</span>
                      <span>${item.product.price}</span>
                    </div>

                    <div className="quantity">
                      <button
                        onClick={() =>
                          updateCart(
                            item.product.id,
                            item.quantity + 1,
                            item.product.quantity,
                            false
                          )
                        }
                        className="plus-btn"
                        type="button"
                        name="button"
                      >
                        <AiOutlinePlus size={20} />
                      </button>
                      <input type="text" name="name" value={item.quantity} />
                      <button
                        onClick={() =>
                          updateCart(
                            item.product.id,
                            item.quantity - 1,
                            item.product.quantity,
                            false,
                            "minus"
                          )
                        }
                        className="minus-btn"
                        type="button"
                        name="button"
                      >
                        <AiOutlineMinus size={20} />
                      </button>

                      <div style={{fontWeight: "600", marginTop: "8px"}}>
                        {item.product.quantity > 0 ? (
                          <>
                            {`(${item.product.quantity + " left"})`}
                          </>
                        ): 
                          <div className="out-of-stock">Out of stock</div>
                        }
                      </div>
                    </div>

                    <div className="total-price">
                      <div>${item.price}</div>
                      <div
                        style={{ cursor: "pointer" }}
                        onClick={() =>
                          updateCart(item.product.id, 0, 100, true)
                        }
                      >
                        <Delete htmlColor="#E53631" size={28} />
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </Wrapper>
          ) : (
            <Wrapper>
            <div className="empty">Your cart is empty!</div>
            </Wrapper>
          )}
        </>
      ) : null}

      <Footer />
    </div>
  );
};

const Wrapper = styled.article`
  .address {
    margin: 80px auto;
    padding: 20px 80px;
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;
    column-gap: 32px;
  }

  .empty {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    background-color: #fff;
    padding: 80px 10px;
    height: 100vh;
    font-size: 18px;
    font-weight: 600;
    flex: 1;
  }

  .shopping-cart {
    background: #ffffff;
    box-shadow: 1px 2px 3px 0px rgba(0, 0, 0, 0.1);
    border-radius: 6px;

    display: flex;
    flex-direction: column;
    margin-bottom: 80px;
  }

  .title {
    height: 60px;
    border-bottom: 1px solid #e1e8ee;
    padding: 20px 30px;
    color: #5e6977;
    font-size: 18px;
    font-weight: 400;
  }

  .item {
    padding: 20px 80px;
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;
  }

  .item:nth-child(3) {
    border-top: 1px solid #e1e8ee;
    border-bottom: 1px solid #e1e8ee;
  }

  .buttons {
    position: relative;
    padding-top: 30px;
    margin-right: 60px;
  }
  .delete-btn,
  .like-btn {
    display: inline-block;
    cursor: pointer;
  }
  .delete-btn {
    width: 18px;
    height: 17px;
    background: url(&amp;quot;delete-icn.svg&amp;quot;) no-repeat center;
  }

  .like-btn {
    position: absolute;
    top: 9px;
    left: 15px;
    background: url("twitter-heart.png");
    width: 60px;
    height: 60px;
    background-size: 2900%;
    background-repeat: no-repeat;
  }

  .btn {
    padding: 5px 15px;
    background-color: #4faf61;
    color: #fff;
    border-radius: 3px;
    margin-left: -2px;
    width: fit-content;
  }

  .is-active {
    animation-name: animate;
    animation-duration: 0.8s;
    animation-iteration-count: 1;
    animation-timing-function: steps(28);
    animation-fill-mode: forwards;
  }

  @keyframes animate {
    0% {
      background-position: left;
    }
    50% {
      background-position: right;
    }
    100% {
      background-position: right;
    }
  }

  .image {
    margin-right: 50px;
  }

  .description {
    padding-top: 10px;
    margin-right: 60px;
    width: 280px;
  }

  span {
    line-break: unset;
  }

  .description span {
    display: block;
    font-size: 14px;
    color: #43484d;
    font-weight: 400;
  }

  .description span:first-child {
    margin-bottom: 5px;
  }
  .description span:last-child {
    font-weight: 300;
    margin-top: 8px;
    color: #86939e;
  }

  .quantity {
    display: block;
  }

  .quantity input {
    -webkit-appearance: none;
    border: none;
    text-align: center;
    width: 32px;
    font-size: 16px;
    color: #43484d;
    font-weight: 300;
  }

  button[class*="btn"] {
    width: 30px;
    height: 30px;
    background-color: #e1e8ee;
    border-radius: 6px;
    border: none;
    cursor: pointer;
  }
  .minus-btn img {
    margin-bottom: 3px;
  }
  .plus-btn img {
    margin-top: 2px;
  }

  button:focus,
  input:focus {
    outline: 0;
  }

  img {
    border: solid 1px #eeeeee;
    width: 120px;
    object-fit: cover;
  }

  .total-price {
    width: 120px;
    text-align: center;
    font-size: 16px;
    color: #43484d;
    font-weight: 300;
    display: flex;
    align-items: center;
    justify-items: flex-end;
    justify-content: space-between;
  }

  @media (max-width: 800px) {
    .shopping-cart {
      width: 100%;
      height: auto;
      overflow: hidden;
    }

    .address {
      margin: 80px auto;
      padding: 20px 80px;
      display: flex;
      flex-direction: column;
      width: 100%;
      justify-content: space-between;
      align-items: center;
      row-gap: 16px;
    }

    .item {
      height: auto;
      flex-wrap: wrap;
      justify-content: center;
    }
    .image img {
      width: 50%;
    }
    .image,
    .quantity,
    .description {
      width: 100%;
      text-align: center;
      margin: 6px 0;
    }
    .buttons {
      margin-right: 20px;
    }
  }
`;

export default Cart;
