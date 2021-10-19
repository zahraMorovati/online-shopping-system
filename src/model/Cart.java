package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int id;
    private StatusCart status;
    private double totalPrice;
    private int productsCount;
    private final int maxItems=5;
    List<Product> productList =new ArrayList<>();

    public Cart(StatusCart status, double totalPrice, int productsCount) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.productsCount = productsCount;
    }

    public Cart(int id, StatusCart status, double totalPrice, int numberOfGoods) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.productsCount = numberOfGoods;
    }

    public enum StatusCart {
        FINISHED,ONGOING;
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

    public StatusCart getStatus() {
        return status;
    }

    public void setStatus(StatusCart status) {
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
