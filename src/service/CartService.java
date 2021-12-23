package service;

import dao.DaoCart;
import model.Cart;
import model.enumation.StatusCart;

import java.util.List;

public class CartService {
    DaoCart daoCart = new DaoCart();

    public Cart newCart(){
        return Cart.CartBuilder.aCart()
                .setProductsCount(0)
                .setStatus(StatusCart.ONGOING)
                .setTotalPrice(0).build();
    }

    public void save(Cart cart){
        daoCart.save(cart);
    }

}
