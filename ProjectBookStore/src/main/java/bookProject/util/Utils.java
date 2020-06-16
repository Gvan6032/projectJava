package bookProject.util;

import bookProject.model.Cart;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static Cart getCartInSession(HttpServletRequest request) {

        Cart cart = (Cart) request.getSession().getAttribute("myCart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("myCart", cart);
        }
        return cart;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static Cart getLastOrderedCartInSession(HttpServletRequest request) {
        return (Cart) request.getSession().getAttribute("lastOrderedCart");
    }
}
