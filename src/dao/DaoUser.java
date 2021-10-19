package dao;

import model.Product;
import model.User;
import model.enumation.ProductGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<User> findAllUsers() throws SQLException {

        if (getConnection() != null) {
            List<User> userList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                String firstName=resultSet.getString("first_name");
                String last_name=resultSet.getString("last_name");
                int nationalCode=resultSet.getInt("nationalCode");
                String phoneNumber=resultSet.getString("phoneNumber");
                Date birthDate=resultSet.getDate("birthDate");
                String email=resultSet.getString("email");
                int cart_id=resultSet.getInt("cart_id");
                String user_name=resultSet.getString("user_name");
                String password=resultSet.getString("password");
                //TODO
            }
            return userList;
        } else {
            return Collections.emptyList();
        }
    }


}
