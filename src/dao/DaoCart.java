package dao;

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
                "    id_user INT," +
                "    PRIMARY KEY (id) ," +
                "    FOREIGN KEY (id_user) REFERENCES user(id))");
    }


}
