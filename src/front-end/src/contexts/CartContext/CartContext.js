import { createContext, useContext, useEffect, useState } from "react";
import { ServiceCart } from "../../services/CartService";
import { useUserContext } from "../UserContext/UserContext";

const CartContext = createContext();


export function CartProvider({ children }) {
  const [cart, setCart] = useState(null);
  const [user, setUser] = useUserContext()

  useEffect(() => {
    const getCartItems = async () => {
        await ServiceCart.Get_Cart(
          null,
          user?.user?.accessToken
        )
          .then((res) => { 
            setCart(res);
          })
         
      };
     if(user?.status === "completed" && user?.user?.accessToken)
     getCartItems();
  }, [user]);

  return (
    <CartContext.Provider value={[cart, setCart]}>
      {children}
    </CartContext.Provider>
  );
}

export function useCartContext() {
  return useContext(CartContext);
}
