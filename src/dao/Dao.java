package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
    private String dataBase = "online_shopping_dao";
    private String dataBaseUser = "online_shopping_user";
    private String dataBasePassword = "1234";
    private boolean dropAllTableOnApplicationStartup = true;

    private static Connection connection;
    public Dao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "george1378");
        Statement statement = con.createStatement();
        statement.executeUpdate(String.format("create user if not exists '%s' identified by '%s'", dataBaseUser, dataBasePassword));
        statement.executeUpdate("create database if not exists " + dataBase);
        statement.executeUpdate(String.format("grant all privileges on `%s`.* to '%s'", dataBase, dataBaseUser));

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBase, dataBaseUser, dataBasePassword);
    }

    public static Connection getConnection() {
        return connection;
    }

    public boolean isDropAllTableOnApplicationStartup() {
        return dropAllTableOnApplicationStartup;
    }
}
