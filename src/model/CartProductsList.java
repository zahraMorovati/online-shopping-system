package model;

public class CartProductsList {

    private int id;
    private int cartId;
    private int userId;
    private int productId;

    public CartProductsList(int id, int cartId, int userId, int productId) {
        this.id = id;
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
    }

    public CartProductsList(int cartId, int userId, int productId) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
