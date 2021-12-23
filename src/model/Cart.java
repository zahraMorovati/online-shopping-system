package model;

import lombok.Data;
import model.enumation.StatusCart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private StatusCart status;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "products_count")
    private int productsCount;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    List<Product> productList =new ArrayList<>();
    @Override
    public String toString() {
        return
                "id=" + id +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", productsCount=" + productsCount ;
    }


    public static final class CartBuilder {
        private Cart cart;

        private CartBuilder() {
            cart = new Cart();
        }

        public static CartBuilder aCart() {
            return new CartBuilder();
        }

        public CartBuilder setId(int id) {
            cart.setId(id);
            return this;
        }

        public CartBuilder setStatus(StatusCart status) {
            cart.setStatus(status);
            return this;
        }

        public CartBuilder setTotalPrice(double totalPrice) {
            cart.setTotalPrice(totalPrice);
            return this;
        }

        public CartBuilder setProductsCount(int productsCount) {
            cart.setProductsCount(productsCount);
            return this;
        }

        public CartBuilder setProductList(List<Product> productList) {
            cart.setProductList(productList);
            return this;
        }

        public Cart build() {
            return cart;
        }
    }
}
