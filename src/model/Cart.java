package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int id;
    private User user;
    private boolean status;
    private double totalPrice;
    private int goodsCount;
    private final int maxItems=5;
    List<Product> productList =new ArrayList<>();

    public Cart(int id, User user, boolean status, double totalPrice, int numberOfGoods, List<Product> productList) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.totalPrice = totalPrice;
        this.goodsCount = numberOfGoods;
        this.productList = productList;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public List<Product> getGoodList() {
        return productList;
    }

    public void setGoodList(List<Product> productList) {
        this.productList = productList;
    }
}
