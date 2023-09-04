import React, { useEffect } from "react";
import styled from "styled-components";
import ProductItem from "./ProductItem";
import { useState } from "react";
import menu from "../../data";
import items from "../../data";
import Categories from "../Categories";
import { CategoriesService } from "../../services/CategoryService";



const ProductList = () => {

  const [categories, setCategories] = useState(null)
  const [selectedCategory, setSelectedCategory] = useState(null)
  const [categoriesError, setCategoriesError] = useState(null)

  useEffect(() => {
    setCategoriesError(null)
    const getCategories = async () => {
      await CategoriesService.Get_All_Categories().then((res) => {
        setCategories(res)
      }).catch((e) => {
        setCategoriesError(e.message)
      })
    }

    getCategories()
  }, [])

  
  return (
    <Wrapper className="section">
      <main>
        <section className="menu section">
          <div className="title">
            <h2>our Products</h2>
            <div className="underline"></div>
          </div>
          <Categories categories={categories} setSelectedCategory={setSelectedCategory} />
          <ProductItem selectedCategory={selectedCategory} />
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
