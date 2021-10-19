import dao.DaoCart;
import dao.DaoProduct;
import dao.DaoUser;
import model.Cart;
import model.Product;
import model.User;
import model.enumation.ProductGroup;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DaoUser daoUser=new DaoUser();
        Date bitthDate=new Date(1980,10,2);
        User user=new User("ali","ahmadi",426499,"0915979",
                bitthDate,"fjsdhk@gmail.com","ali1234","1234",1);
        daoUser.save(user);




    }
}
