package dao;

import model.Cart;

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
                "    status boolean DEFAULT false," +
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

    public Cart findCartByID(int cartID) throws SQLException {

        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("select * from cart where id='%d'",cartID);
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



}
