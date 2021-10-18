package model;

import model.enumation.ProductGroup;

public class Product {
    private int id;
    private String name;
    private ProductGroup productGroup;
    private ProductGroup.ProductType productType;
    private double price;
    private int count;

    public Product(int id, String name, ProductGroup productGroup, ProductGroup.ProductType productType, double price, int count) {
        this.id = id;
        this.name = name;
        this.productGroup = productGroup;
        this.productType = productType;
        this.price = price;
        this.count = count;
    }

    public Product(String name, ProductGroup productGroup, ProductGroup.ProductType productType, double price, int count) {
        this.name = name;
        this.productGroup = productGroup;
        this.productType = productType;
        this.price = price;
        this.count = count;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public ProductGroup.ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductGroup.ProductType productType) {
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductGroup getGoodType() {
        return productGroup;
    }

    public void setGoodType(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
