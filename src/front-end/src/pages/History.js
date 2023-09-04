import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar/Navbar";
import Footer from "../components/Footer/Footer";
import styled from "styled-components";
import axios from "axios";
import { useUserContext } from "../contexts/UserContext/UserContext";

const History = () => {
  const [orders, setOrders] = useState(null);
  const [user, setUser] = useUserContext()

  useEffect(() => {
    if (user?.status === "completed" && !user?.user?.accessToken) {
      window.location.replace("/");
    }
  }, [user]);

  useEffect(() => {
    const getHistory = async () => {
      await axios
        .get(`${process.env.REACT_APP_API_URL}/order`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${user?.user?.accessToken}`,
          },
        })
        .then((res) => {
          setOrders(res.data);
        });
    };

    if (user?.status === "completed" && user?.user?.accessToken) getHistory();
  }, [user]);
  return (
    <div>
      <Navbar />
      {user?.user?.accessToken && user?.status === "completed" ? (
        <Wrapper>
        <div className="history">
          {orders?.map((order) => (
            <div className="history-item">
              <div className="history-item-top">
                <div>
                  <div>
                    <span className="bold">Address : </span>
                    {order.address ? order.address : "--"}
                  </div>
                  <div>
                    <span className="bold">Phone number : </span>
                    {order.phoneNumber ? order.phoneNumber : "--"}
                  </div>
                  <div>
                    <span className="bold">Creation Date : </span>
                    {order.orderDate[2] +
                      "/" +
                      order.orderDate[1] +
                      "/" +
                      order.orderDate[0] +
                      "/"}
                  </div>
                </div>
                <div>
                  <div>
                    <span className="bold">Order status : </span>
                    {order.orderStatus}
                  </div>
                  <div>
                    <span className="bold">Subtotal : </span>${order.subtotal}
                  </div>
                </div>
              </div>

              <div className="products">
                {order?.orderDetailSet?.map((item) => (
                  <div key={item.id} className="product">
                    <h4>{item.product.name} :</h4>
                    <div>${item.product.price} x {item?.quantity} = ${item?.unitPrice}</div>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      </Wrapper>
      ): null}
      
      <Footer />
    </div>
  );
};

const Wrapper = styled.article`
  .history {
    background: #ffffff;
    box-shadow: 1px 2px 3px 0px rgba(0, 0, 0, 0.1);
    border-radius: 6px;
    row-gap: 32px;
    display: flex;
    flex-direction: column;
    margin: 80px auto;
  }

  .bold {
    font-weight: 600;
  }

  .history-item {
    padding: 20px 80px;
    display: flex;
    width: 100%;
    column-gap: 32px;
    flex-direction: row-reverse;
    justify-content: space-between;
    border-bottom: 1px solid #ccc;
  }

  .history-item > .products > div:last-child {
    border: none;
  }

  .products {
    display: flex;
    flex-direction: column;
  }

  .product {
    padding: 18px 0;
    border-bottom: solid 1px #ccc;
  }

  h4 {
    padding: 0;
    margin: 0;
  }

  @media (max-width: 800px) {
    .history-item {
      flex-direction: column;
      padding: 20px;
      border-bottom: 2px solid, #ccc;
    }
  }
`;

export default History;
