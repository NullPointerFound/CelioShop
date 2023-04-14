import Home from "./pages/Home";
import { createBrowserRouter, RouterProvider, Outlet } from "react-router-dom";
import ProductPage from "./pages/ProductPage";
import Register from "./pages/Register";
import Signin from "./pages/SignIn";
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import "./app.scss";
import ProductList from "./components/Products/ProductList";

const Layout = () => {
  return (
    <div className="app">
      <Navbar />
      <ProductList />
      <Outlet />
      <Footer />
    </div>
  );
};

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/signin",
    element: <Signin />,
  },
  {
    path: "/register",
    element: <Register />,
  },
]);

function App() {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
