package dao;

import java.sql.*;

public class DaoUser extends Dao{

    public DaoUser() throws ClassNotFoundException, SQLException {

        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "user", null);
            if (!tables.next()) {
                createUserTable();
            }
        }
    }

    private void createUserTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE user (" +
                "    id INT NOT NULL  AUTO_INCREMENT," +
                "    first_name VARCHAR(25)," +
                "    last_name VARCHAR(25)," +
                "    nationalCode INT," +
                "    phoneNumber VARCHAR(11)," +
                "    birthDate Date," +
                "    email VARCHAR(25)," +
                "    cart_id INT," +
                "    user_name VARCHAR(25)," +
                "    password VARCHAR(25)," +
                "    status boolean DEFAULT false," +
                "    PRIMARY KEY (id) ," +
                "    FOREIGN KEY (cart_id) REFERENCES cart(id))");
    }
}
