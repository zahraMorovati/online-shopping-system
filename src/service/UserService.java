package service;

import dao.Dao;
import dao.DaoProduct;
import dao.DaoUser;
import model.Cart;
import model.Product;
import model.User;

import java.sql.Date;
import java.util.List;

import static model.enumation.ConsoleColors.RESET;
import static model.enumation.ConsoleColors.YELLOW;
import static view.GetValidData.*;

public class UserService {

   static DaoUser daoUser = new DaoUser();
   DaoProduct daoProduct = new DaoProduct();

    public User getUserByUsernameAndPassword(String username,String password){
        User user = daoUser.findUserByUserNameAndPassword(username, password);
        if(user!=null)
            return user;
        else throw  new RuntimeException("wrong username or pass!");
    }

    public void save(User user){
        daoUser.save(user);
        System.out.println("user successfully saved!");
    }

    public boolean isDupplicatedUsername(String username){
       return daoUser.checkDuplicatedUser(username);
    }

    public static User getUserInfo() {
        String userName = getValidString("userName: ");
        if (!daoUser.checkDuplicatedUser(userName) && !userName.equalsIgnoreCase("admin")) {
            return getUser(userName);
        } else {
            System.out.println(YELLOW + "invalid username!" + RESET);
            return getUserInfo();
        }
    }

    private static User getUser(String userName) {
        String password = getValidString("password :");
        String firstName = getValidName("first name: ");
        String last_name = getValidName("last name: ");
        int nationalCode = getValidInt("national code: ");
        String phoneNumber = getValidPhoneNumber("phoneNumber: ");
        Date birthDate = Date.valueOf(getValidBirthDate().toString());
        String email = getValidString("email: ");
        double balance = getValidDouble("balance: ");
        return setUserInfo(userName, password, firstName, last_name, nationalCode, phoneNumber, birthDate, email, balance);
    }

    private static User setUserInfo(String userName, String password, String firstName, String last_name, int nationalCode, String phoneNumber, Date birthDate, String email, double balance) {
        User user = User.UserBuilder.anUser().setFirstName(firstName)
                .setLastName(last_name)
                .setNationalCode(nationalCode)
                .setPhoneNumber(phoneNumber)
                .setBirthDate(birthDate)
                .setEmail(email)
                .setUserName(userName)
                .setPassword(password)
                .setBalance(balance).build();
        return user;
    }


    public void confirmCart(int userId){
        daoUser.confirmCart(userId);
    }

    public void addToCart(int userId, Product product){
        daoUser.addToCart(userId,product);
        daoProduct.updateProductCount(-1,product.getId());
    }

    public Cart getUserCart(int id){
        User user = daoUser.findById(id).get(0);
        if(user!=null)
            return user.getCart();
        else throw new RuntimeException("cannot find user!");

    }

    public List<Product> getUserCartProducts(int id){
        User user = daoUser.findById(id).get(0);
        if(user!=null)
            return user.getCart().getProductList();
        else throw new RuntimeException("cannot find user!");

    }

    public void removeFromCart(int userId,Product product){
        daoUser.removeFromCart(userId,product);
        daoProduct.updateProductCount(1,product.getId());
    }
}
