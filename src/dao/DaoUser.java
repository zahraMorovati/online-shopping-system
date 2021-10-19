package dao;

import model.User;

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
                "    PRIMARY KEY (id) ," +
                "    FOREIGN KEY (cart_id) REFERENCES cart(id))");
    }

    public int save(User u) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO user" +
                            " (first_name,last_name,nationalCode,phoneNumber,birthDate,email,cart_id,user_name,password) " +
                            "VALUES ('%d','%s','%s','%d','%s','%s','%s','%d','%s','%s','%d')",
                    u.getFirstName(),u.getLastName(),u.getNationalCode(),u.getPhoneNUmber(),u.getBirthDate(),
                    u.getEmail(),u.getCart().getId(),u.getUserName(),u.getPassword());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }
}
