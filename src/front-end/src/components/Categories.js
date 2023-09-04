import React from "react";
import styled from "styled-components";

function Categories({ categories, setSelectedCategory }) {
  return (
    <Wrapper>
      <div className="btn-container">
        <button
          type="button"
          className="filter-btn"
          onClick={() => setSelectedCategory(null)}
        >
          All
        </button>
        {categories?.map((category, index) => {
          return (
            <button
              type="button"
              className="filter-btn"
              key={category.id}
              onClick={() => setSelectedCategory(category.id)}
            >
              {category.name}
            </button>
          );
        })}
      </div>
    </Wrapper>
  );
}

const Wrapper = styled.article`
  .btn-container {
    margin-top: 2rem;
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
    color: #27ae60;
    cursor: pointer;
    transition: var(--transition);
    border-radius: var(--radius);
  }
  .filter-btn:hover {
    background: #27ae60;
    color: var(--clr-white);
  }
`;

export default Categories;
