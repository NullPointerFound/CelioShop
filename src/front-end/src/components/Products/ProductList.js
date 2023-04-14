import React from "react";
import styled from "styled-components";
import ProductItem from "./ProductItem";
import { useState } from "react";
import menu from "../../data";
import items from "../../data";
import Categories from "../Categories";

const allCategories = ["all", ...new Set(items.map((item) => item.category))];

const ProductList = () => {
  const [categoryItems, setCategoryItems] = useState(menu);
  const [categories, setCategories] = useState(allCategories);

  const filterItems = (category) => {
    if (category === "all") {
      setCategoryItems(menu);
      return;
    }
    const newItems = menu.filter((item) => item.category === category);
    setCategoryItems(newItems);
  };
  return (
    <Wrapper className="section">
      <main>
        <section className="menu section">
          <div className="title">
            <h2>our Products</h2>
            <div className="underline"></div>
          </div>
          <Categories categories={categories} filterItems={filterItems} />
          <ProductItem items={categoryItems} />
        </section>
      </main>
    </Wrapper>
  );
};

const Wrapper = styled.section`
  
  .featured {
    margin: 4rem auto;
    display: grid;
    gap: 2.5rem;
    img {
      height: 225px;
    }
  }
  @media (min-width: 576px) {
    .featured {
      grid-template-columns: repeat(auto-fit, minmax(360px, 1fr));
    }
  }
`;

export default ProductList;
