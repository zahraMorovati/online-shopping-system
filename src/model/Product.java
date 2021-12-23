package model;

import lombok.Data;
import model.enumation.ProductGroup;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "product_group")
    private ProductGroup productGroup;
    @Column(name ="product_Type" )
    private ProductGroup.ProductType productType;
    private double price;
    private int count;


    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", productGroup=" + productGroup +
                ", productType=" + productType +
                ", price=" + price +
                ", count=" + count;
    }


    public static final class ProductBuilder {
        private Product product;

        private ProductBuilder() {
            product = new Product();
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder setId(int id) {
            product.setId(id);
            return this;
        }

        public ProductBuilder setName(String name) {
            product.setName(name);
            return this;
        }

        public ProductBuilder setProductGroup(ProductGroup productGroup) {
            product.setProductGroup(productGroup);
            return this;
        }

        public ProductBuilder setProductType(ProductGroup.ProductType productType) {
            product.setProductType(productType);
            return this;
        }

        public ProductBuilder setPrice(double price) {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder setCount(int count) {
            product.setCount(count);
            return this;
        }

        public ProductBuilder but() {
            return aProduct().setId(product.getId()).setName(product.getName()).setProductGroup(product.getProductGroup()).setProductType(product.getProductType()).setPrice(product.getPrice()).setCount(product.getCount());
        }

        public Product build() {
            return product;
        }
    }
}
