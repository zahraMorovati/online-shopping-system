package dao;

import model.Cart;

import java.sql.*;

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
                "    id_user INT," +
                "    status boolean DEFAULT false," +
                "    total_price DOUBLE ," +
                "    products_count INT," +
                "    PRIMARY KEY (id) ," +
                "    FOREIGN KEY (id_user) REFERENCES user(id))");
    }

    public int save(Cart c) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO cart" +
                            " (id_user,status,total_price,products_count) " +
                            "VALUES ('%d','%d','%f','%d')",
                    c.getUser().getId(),c.getStatus(),c.getTotalPrice(),c.getProductsCount());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }



}
