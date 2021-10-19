package dao;

import model.Cart;
import model.CartProductsList;

import java.sql.*;

public class DaoCartProductsList extends Dao{

    public DaoCartProductsList() throws ClassNotFoundException, SQLException {

        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "cartProductsList", null);
            if (!tables.next()) {
                createCartProductsListTable();
            }
        }
    }

    private void createCartProductsListTable() throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE cartProductsList (" +
                "    id INT NOT NULL  AUTO_INCREMENT," +
                "    id_cart INT ," +
                "    id_user INT ," +
                "    id_product INT," +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (id_cart) REFERENCES cart(id)," +
                "    FOREIGN KEY (id_user) REFERENCES user(id)," +
                "    FOREIGN KEY (id_product) REFERENCES product(id))");
    }

    public int save(CartProductsList c) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO cartProductsList" +
                            " (id_cart,id_user,id_product) " +
                            "VALUES ('%d','%d','%d')",
                    c.getCartId(),c.getUserId(),c.getProductId());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public int delete(int cartId) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("delete from cartProductsList where id_cart='%d'",cartId);
            int i = statement.executeUpdate(sqlQuery);
            return i;
        }else {
            return 0;
        }
    }




}
