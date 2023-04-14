import React from "react";
import Navbar from "../components/Navbar/Navbar";
import ProductList from "../components/Products/ProductList";
import Footer from "../components/Footer/Footer";

function Home() {
  return (
    <>
      <Navbar />
      <ProductList />
      <Footer />
    </>
  );
}

export default Home;
