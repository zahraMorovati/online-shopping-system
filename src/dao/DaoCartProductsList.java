package dao;

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
                "    PRIMARY KEY (id)" +
                "    FOREIGN KEY (id_cart) REFERENCES cart(id)" +
                "    FOREIGN KEY (id_user) REFERENCES user(id)" +
                "    FOREIGN KEY (id_product) REFERENCES product(id))");
    }


}
