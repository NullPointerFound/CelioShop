import React from "react";
import styled from "styled-components";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Link } from "react-router-dom";

function Navbar() {
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
              <Link to="/SignIn">
                <PersonOutlineIcon />
              </Link>
              <Link to="/cart" className="cartIcon">
                <ShoppingCartIcon />
                <span>0</span>
              </Link>
            </div>
          </div>
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
  .navbar {
    height: 60px;
    background: var(--clr-white);
    box-shadow: 0.25em 0.25em 2em rgba(0, 0, 0, 0.15);

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
`;
export default Navbar;
