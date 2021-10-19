package dao;

import model.Cart;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoCart extends Dao {

    public DaoCart() throws ClassNotFoundException, SQLException {

        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "cart", null);
            if (!tables.next()) {
                createCartTable();
            }
        }
    }

    private void createCartTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE cart (" +
                "    id INT NOT NULL  AUTO_INCREMENT," +
                "    status  VARCHAR(20)," +
                "    total_price DOUBLE ," +
                "    products_count INT," +
                "    PRIMARY KEY (id))");
    }

    public int save(Cart c) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO cart" +
                            " (status,total_price,products_count) " +
                            "VALUES ('%s','%f','%d')",
                    c.getStatus().toString(),c.getTotalPrice(),c.getProductsCount());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public List<Cart> findAllCarts() throws SQLException {

        if (getConnection() != null) {
            List<Cart> cartList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cart");
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                Cart.StatusCart status= Cart.StatusCart.valueOf(resultSet.getString("status"));
                double totalPrice=resultSet.getDouble("total_price");
                int productsCount=resultSet.getInt("products_count");
                Cart cart=new Cart(id,status,totalPrice,productsCount);
                cartList.add(cart);
            }
            return cartList;
        } else {
            return Collections.emptyList();
        }
    }

    public Cart findCartByUserID(int userId) throws SQLException {

        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("SELECT cart.id,cart.status,cart.total_price,cart.products_count\n" +
                    "from user inner join cart\n" +
                    "where cart_id=user.cart_id\n" +
                    "and user.id='%d'",userId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                Cart.StatusCart status= Cart.StatusCart.valueOf(resultSet.getString("status"));
                double totalPrice=resultSet.getDouble("total_price");
                int productsCount=resultSet.getInt("products_count");
                Cart cart=new Cart(id,status,totalPrice,productsCount);
                return cart;
            }
        } else {
            return null;
        }
        return null;
    }

    public int updateCartStatus(int cartID, Cart.StatusCart statusCart) throws SQLException {

        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("update cart set status='%s' where id='%d'",statusCart.toString(),cartID);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
               return 1;
            }
        } else {
            return -1;
        }
        return -1;

    }

    public int insertIntoCart(int cartID, Product product,int count) throws SQLException {

        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("update cart set products_count=(products_count)+'%d' and " +
                    "total_price=(total_price)+'%f' where id='%d' and products_count>5",count,product.getPrice(),cartID);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                return 1;
            }
        } else {
            return -1;
        }
        return -1;

    }

    public int deleteFromCart(int cartID, Product product) throws SQLException {

        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("update cart set products_count=(products_count)-1 and " +
                    "total_price=(total_price)-'%f' where id='%d'",product.getPrice(),cartID);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                return 1;
            }
        } else {
            return -1;
        }
        return -1;

    }

    public int getLastIdCart() throws SQLException {
        int id=0;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("select id from cart");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                id=id+1;
            }
        } else {
            return -1;
        }
        return id;
    }





}
