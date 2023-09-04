import Home from "./pages/Home";
import { createBrowserRouter, RouterProvider, Outlet } from "react-router-dom";
import ProductPage from "./pages/ProductPage";
import Register from "./pages/Register";
import Signin from "./pages/SignIn";
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import "./app.scss";
import ProductList from "./components/Products/ProductList";
import Cart from "./pages/Cart";
import History from "./pages/History";
import Error from "./pages/Error";

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
  {
    path: `/product/:id`,
    element: <ProductPage/>
  },
  {
    path: `/cart`,
    element: <Cart/>
  },
  {
    path: `/history`,
    element: <History/>
  },
  {path:'*',
   element: <Error/> 
}
]);

function App() {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
