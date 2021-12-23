import model.Product;
import model.User;
import model.enumation.ConsoleColors;
import service.CartService;
import service.ProductService;
import service.UserService;
import view.GetValidData;
import view.PrintOutPut;

public class Main {
    public static final String usernameAdmin = "admin";
    public static final String passwordAdmin = "1234";

    public static void main(String[] args) {
        startMenu();
    }

    public static void startMenu() {
        UserService userService = new UserService();
        try {
            while (true){
                int choice = GetValidData.getValidChoice("1)add new user \n" +
                        "2)login \n" +
                        "3)exit \n" +
                        "enter your choice:", 3);

                switch (choice) {
                    case 1: {
                        User user = UserService.getUserInfo();
                        userService.save(user);
                        startMenu();
                    }
                    break;
                    case 2: {
                        loginMenu(userService);
                    }
                    break;
                    case 3: {
                        System.exit(0);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(ConsoleColors.RED+"Exception: "+e.getMessage()+ConsoleColors.RESET);
            startMenu();
        }

    }

    private static void loginMenu(UserService userService) {
        String username = GetValidData.getValidString("username: ");
        String password = GetValidData.getValidString("password: ");
        if(username.equals(usernameAdmin) && password.equals(passwordAdmin)){
            adminMainMenu();
        }
        User user = userService.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            mainMenu(user);
        } else {
            System.out.println("wrong username or password!");
            startMenu();
        }
    }

    public static void mainMenu(User user) {
        ProductService productService = new ProductService();
        UserService userService = new UserService();
        CartService cartService = new CartService();
        try {
            while (true) {
                int choice = GetValidData.getValidChoice("1)add to cart\n" +
                        "2)delete from cart \n" +
                        "3)print cart \n" +
                        "4)confirm cart \n" +
                        "5)exit \n" +
                        "enter your choice:", 5);

                switch (choice) {
                    case 1: {
                        PrintOutPut.printProductList(productService.getListProducts());
                        int productId = GetValidData.getValidInt("enter your choice: ");
                        Product product = productService.getProductById(productId);
                        userService.addToCart(user.getId(),product);
                    }
                    break;
                    case 2: {
                        PrintOutPut.printProductList(userService.getUserCartProducts(user.getId()));
                        int productId = GetValidData.getValidInt("enter your choice: ");
                        Product product = productService.getProductById(productId);
                        userService.removeFromCart(user.getId(),product);

                    }
                    break;
                    case 3: {
                        System.out.println(userService.getUserCart(user.getId()));
                    }
                    break;
                    case 4: {
                        userService.confirmCart(user.getId());
                    }
                    break;
                    case 5: {
                        System.exit(0);
                    }
                }
            }

        }catch (Exception e){
            System.out.println(ConsoleColors.RED+"Exception: "+e.getMessage()+ConsoleColors.RESET);
            mainMenu(user);
        }

    }

    public static void adminMainMenu() {
        ProductService productService = new ProductService();
        try {
            while (true) {
                int choice = GetValidData.getValidChoice("1)add product 2)loginMenu 3)exit \n" +
                        "enter your choice: ", 3);
                switch (choice){
                    case 1:{
                        Product product = productService.getProduct();
                        productService.save(product);
                    }break;
                    case 2:{
                        startMenu();
                    }break;
                    case 3:{
                        System.exit(0);
                    }break;
                }
            }
        }catch (Exception e){
            System.out.println(ConsoleColors.RED+"Exception: "+e.getMessage()+ConsoleColors.RESET);
            adminMainMenu();
        }

    }
}
