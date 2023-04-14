import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const ProductItem = ({ items }) => {
  return (
    <Wrapper>
      <div className="section-center">
        {items.map((menuItem) => {
          const { id, title, img, desc, price } = menuItem;
          return (
            <article key={id} className="menu-item">
              <div className="item-info">
                <Link to={`/product/${id}`}>
                  <header>
                    <h4>{title}</h4>
                    <h4 className="price">${price}</h4>
                  </header>
                  <div>
                    <img src={img} alt={title} className="photo" />
                  </div>
                </Link>
                <p className="item-text">{desc}</p>
                <div class="item-action">
                  <input
                    className="action-button"
                    type="submit"
                    value="ADD TO CART"
                  />
                  <input
                    className="action-button"
                    type="submit"
                    value="BUY NOW"
                  />
                </div>
              </div>
            </article>
          );
        })}
      </div>
    </Wrapper>
  );
};

const Cardcontainer = styled.article`
  width: 1000px;
`;

const Wrapper = styled.article`
  .menu {
    padding: 3rem 0;
  }
  .title {
    text-align: center;
    margin-bottom: 2rem;
  }
  .underline {
    width: 5rem;
    height: 0.25rem;
    background: var(--clr-primary-1);
    margin-left: auto;
    margin-right: auto;
  }

  .action-button {
    width: 40%;
    background: var(--clr-primary-1);
    font-weight: bold;
    color: white;
    border: 0 none;
    border-radius: 1px;
    cursor: pointer;
    padding: 10px;
    margin: 10px 5px;
    text-decoration: none;
    font-size: 14px;
    margin-top: 25px;
  }
  .action-button:hover,
  .action-button:focus {
    box-shadow: 0 0 0 2px white, 0 0 0 3px #27ae60;
  }

  .btn-container {
    margin-bottom: 4rem;
    display: flex;
    justify-content: center;
  }
  .filter-btn {
    background: transparent;
    border-color: transparent;
    font-size: 1rem;
    text-transform: capitalize;
    margin: 0 0.5rem;
    letter-spacing: 1px;
    padding: 0.375rem 0.75rem;
    color: var(--clr-gold);
    cursor: pointer;
    transition: var(--transition);
    border-radius: var(--radius);
  }
  .filter-btn:hover {
    background: var(--clr-gold);
    color: var(--clr-white);
  }
  .section-center {
    width: 100vw;
    margin: 0 auto;
    max-width: 1170px;
    display: grid;
    gap: 3rem 2rem;
    justify-items: center;
  }
  .menu-item {
    border-radius: 10px;
    background-color: white;
    box-shadow: 0px 0px 0px 1px #c6c5c5;
    padding: 15px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
    text-align: center;
  }
  .photo {
    max-width: 100%;
    max-height: 100%;
  }
  .item-info header {
    display: flex;
    margin-bottom: 5px;
    justify-content: space-between;
    border-bottom: 0.5px dotted var(--clr-grey-5);
  }
  .item-info h4 {
    margin-bottom: 0.5rem;
  }
  .price {
    color: #27ae60;
  }
  .item-text {
    margin-bottom: 0;
    padding-top: 1rem;
  }

  @media screen and (min-width: 768px) {
    .menu-item {
      grid-template-columns: 225px 1fr;
      gap: 0 1.25rem;
      max-width: 40rem;
    }
    .photo {
      height: 175px;
    }
  }
  @media screen and (min-width: 1200px) {
    .section-center {
      width: 95vw;
      grid-template-columns: 1fr 1fr 1fr;
    }
    .photo {
      height: 200px;
      width: 80%;
    }
  }
`;
export default ProductItem;
