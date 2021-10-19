package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int id;
    private boolean status;
    private double totalPrice;
    private int productsCount;
    private final int maxItems=5;
    List<Product> productList =new ArrayList<>();

    public Cart(boolean status, double totalPrice, int productsCount) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.productsCount = productsCount;
    }

    public Cart(int id, boolean status, double totalPrice, int numberOfGoods) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.productsCount = numberOfGoods;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public int getStatus() {
        if(status)
            return 1;
        else return 0;
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
