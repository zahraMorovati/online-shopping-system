package service;

import dao.DaoCart;
import dao.DaoCartProductsList;
import dao.DaoProduct;
import dao.DaoUser;
import model.Cart;
import model.Product;
import model.User;

import java.sql.SQLException;
import java.util.List;

import static validData.ConsoleColors.*;
import static validData.GetValidData.*;

public class Service {



    public void checkBalanceIsEnough(double amount,int userID){
        //todo
    }

    public void checkProductCountIsEnough(int count,int productId){
        //todo
    }

    public void checkCartCount(int cartId,int count){
        //todo
    }

    public void printUserCart(int userId, DaoCart daoCart, DaoUser daoUser, DaoCartProductsList productsList){
        //todo
    }
    
    public static void saveUser(DaoUser daoUser, User user,DaoCart daoCart) throws SQLException {
        
        Cart cart=new Cart(Cart.StatusCart.ONGOING,0,0);
        daoCart.save(cart);
        user.setCartID(daoCart.getLastIdCart());
        daoUser.save(user);
        
    }


    public static void printUserCart( DaoUser daoUser, DaoProduct daoProduct,DaoCart daoCart) throws SQLException {

        int userId=getValidInt("enter userID:");
        if(daoUser.findUserById(userId)){
            List<Product> productList=daoProduct.findCartProducts(userId);
            Cart cart=daoCart.findCartByUserID(userId);
            System.out.println(cart.toString());
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }else {
            System.out.println(RED+"invalid user id!"+RESET);;
        }
    }
}
