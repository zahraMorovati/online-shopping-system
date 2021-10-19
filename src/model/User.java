package model;

import java.util.Date;

public class User extends Person {

    private Cart cart;
    private String userName;
    private String password;
    private int cartID;

    public User(int id, String firstName, String lastName, int nationalCode, String phoneNUmber, Date birthDate, String email, String userName, String password, int cartID) {
        super(id, firstName, lastName, nationalCode, phoneNUmber, birthDate, email);
        this.userName = userName;
        this.password = password;
        this.cartID = cartID;
    }

    public User(String firstName, String lastName, int nationalCode, String phoneNUmber, Date birthDate, String email, String userName, String password, int cartID) {
        super(firstName, lastName, nationalCode, phoneNUmber, birthDate, email);
        this.userName = userName;
        this.password = password;
        this.cartID = cartID;
    }

    public User(String firstName, String lastName, int nationalCode, String phoneNUmber, Date birthDate,
                String email, Cart cart, String userName, String password) {

        super(firstName, lastName, nationalCode, phoneNUmber, birthDate, email);
        this.cart = cart;
        this.userName = userName;
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
}
