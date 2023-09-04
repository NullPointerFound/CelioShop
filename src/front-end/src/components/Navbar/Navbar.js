import React, { useState } from "react";
import styled from "styled-components";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Link } from "react-router-dom";
import { useCartContext } from "../../contexts/CartContext/CartContext";
import { History, Logout, Menu } from "@mui/icons-material";
import { useUserContext } from "../../contexts/UserContext/UserContext";

function Navbar() {
  const [cart, setCart] = useCartContext();
  const [openMenu, setOpenMenu] = useState(false);
  const [user, setUser] = useUserContext();

  const logoutHandler = () => {
    localStorage.removeItem("userInfo");
    setUser({ user: null, status: "completed" });
    window.location.reload(false);
  };

  return (
    <Wrapper>
      <div className="navbar">
        <div className="wrapper">
          <div className="left">
            <Link to="/">CelioSHOP</Link>
          </div>

          <div className="right">
            <div className="item">
              <Link className="link" to="/">
                Home
              </Link>
            </div>
            <div className="item">
              <Link className="link" to="/">
                About us
              </Link>
            </div>
            <div className="item">
              <Link className="link" to="/">
                TERM OF USE
              </Link>
            </div>
            <div className="item">
              <Link className="link" to="/">
                Contact US
              </Link>
            </div>
            <div className="icons">
              {user?.status === "completed" && user?.user?.accessToken ? (
                <div style={{cursor: "pointer"}} onClick={logoutHandler}>
                  <Logout />
                </div>
              ) : (
                <>
                  <Link to="/SignIn">
                    <PersonOutlineIcon />
                  </Link>
                </>
              )}

              <Link to="/cart" className="cartIcon">
                <ShoppingCartIcon />
                <span>{cart?.items ? cart?.items.length : 0}</span>
              </Link>

              {user?.status === "completed" && user?.user?.accessToken ? (
                <Link to={"/history"}>
                  <History/>
                </Link>
              ): null}
              <div onClick={() => setOpenMenu(!openMenu)} className="hamburger">
                <Menu />
              </div>
            </div>
          </div>
        </div>
        <div
          style={{ display: openMenu ? "flex" : "none" }}
          className="mobile-menu"
        >
          <Link className="link" to="/">
            Home
          </Link>

          <Link className="link" to="/">
            About us
          </Link>

          <Link className="link" to="/">
            Terms of use
          </Link>

          <Link className="link" to="/">
            Contactut Us
          </Link>
        </div>
      </div>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  a {
    color: black;
  }

  .link {
    display: inline-block;
    color: black;
  }

  .link:hover {
    color: var(--clr-primary-1);
  }

  .mobile-menu {
    display: none;
  }

  .hamburger {
    display: none;
  }
  .navbar {
    height: 60px;
    background: var(--clr-white);
    box-shadow: 0.25em 0.25em 2em rgba(0, 0, 0, 0.15);
    position: relative;

    .wrapper {
      padding: 10px 30px;
      display: flex;
      justify-content: space-between;

      .item {
        display: flex;
        align-items: center;
        font-size: 18px;
      }

      .left {
        font-size: 30px;
        letter-spacing: 2px;
      }

      .right {
        display: flex;
        align-items: center;
        gap: 50px;

        .icons {
          display: flex;
          gap: 15px;
          color: black;

          .cartIcon {
            position: relative;
            span {
              font-size: 12px;
              width: 20px;
              height: 20px;
              border-radius: 50%;
              background-color: var(--clr-primary-1);
              color: white;
              position: absolute;
              right: -10px;
              top: -10px;
              display: flex;
              align-items: center;
              justify-content: center;
            }
          }
        }
      }
    }
  }

  @media (max-width: 800px) {
    .item {
      display: none !important;
    }

    .mobile-menu {
      display: flex;
      flex-direction: column;
      row-gap: 12px;
      border: 2px solid #ccc;
      width: 100%;
      align-items: center;
      justify-content: center;
      background-color: #fff;
      z-index: 999;
      position: absolute;
      padding: 12px;
      font-weight: 600;
      font-size: 14px;
    }

    .left {
      font-size: 22px !important;
    }

    .wrapper {
      padding: 10px !important;
    }

    .hamburger {
      display: block;
    }
  }
`;
export default Navbar;
