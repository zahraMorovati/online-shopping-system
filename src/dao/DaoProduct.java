package dao;

import model.Product;
import model.enumation.ProductGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoProduct extends Dao{

    public DaoProduct() throws ClassNotFoundException, SQLException {

        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "product", null);
            if (!tables.next()) {
                createGoodTable();
            }
        }
    }

    private void createGoodTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE product (" +
                "    id INT NOT NULL  AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    group_product VARCHAR(25)," +
                "    type VARCHAR(25)," +
                "    price Double ," +
                "    count INT," +
                "    PRIMARY KEY (id))");
    }

   public int save(Product p) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO product" +
                            " (name,group_product,type,price,count) " +
                            "VALUES ('%s','%s','%s','%f','%d')",
                     p.getName(),p.getProductGroup().toString(),p.getProductType().toString(),
                    p.getPrice(),p.getCount());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }


    public List<Product> findAllProducts() throws SQLException {

        if (getConnection() != null) {
            List<Product> productList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            return getProducts(productList, resultSet);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Product> findCartProducts(int userID) throws SQLException {

        if (getConnection() != null) {
            List<Product> productList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("SELECT product.id ,product.name,product.group_product,product.type," +
                    "product.price,product.count\n" +
                    " FROM ( product inner join user inner join cartproductslist )\n" +
                    "where user.id=cartproductslist.id_user and\n" +
                    "cartproductslist.id_cart=user.cart_id and\n" +
                    "product.id=cartproductslist.id_product and user.id='%d'",userID);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            return getProducts(productList, resultSet);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Product> getProducts(List<Product> productList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            ProductGroup group= ProductGroup.valueOf(resultSet.getString("group_product"));
            ProductGroup.ProductType type= ProductGroup.ProductType.valueOf(resultSet.getString("type"));
            double price=resultSet.getDouble("price");
            int count=resultSet.getInt("count");
            Product product=new Product(id,name,group,type,price,count);
            productList.add(product);
        }
        return productList;
    }


}
