package dao;

import model.Cart;
import model.Product;
import model.User;
import model.enumation.StatusCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.ProductService;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import static model.enumation.StatusCart.FINISHED;

public class DaoUser extends Dao {

    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static Cart newCart = Cart.CartBuilder.aCart().setStatus(StatusCart.ONGOING).build();
    private static final int maxSizeCart = 5;

    /*
     * for save a user
     * first we should add new cart then save the user
     * */
    public void save(User user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        user.setCart(newCart);
        session.save(user);
        transaction.commit();
        session.close();
    }


    /*
     * for confirm cart
     * 1- check balance is enough
     * 2- update balace
     * 3- update status cart
     * 4- add a new cart to user
     * */

    public void confirmCart(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);

        if (user != null && user.getCart() != null) {
            if (user.getCart().getProductsCount() != 0) {
                double newBalance = user.getBalance() - user.getCart().getTotalPrice();
                if (newBalance > 0) {
                    Query<Cart> cartQuery = session.createQuery("update Cart c set c.status=:status where c.id=:id");
                    cartQuery.setParameter("status", FINISHED);
                    cartQuery.setParameter("id", user.getCart().getId());
                    cartQuery.executeUpdate();
                    user.setCart(newCart);
                    user.setBalance(newBalance);
                    session.update(user);
                } else throw new RuntimeException("balance is not enough!");
            } else throw new RuntimeException("your cart is empty you can't confirm it!");
        } else throw new RuntimeException("cannot find user!");

        transaction.commit();
        session.close();
    }


    public List<User> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.id=:id");
        query.setParameter("id", id);
        List<User> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }


    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int nationalCode = resultSet.getInt("nationalCode");
        String phoneNumber = resultSet.getString("phoneNumber");
        Date birthDate = resultSet.getDate("birthDate");
        String email = resultSet.getString("email");
        int cartId = resultSet.getInt("cart_id");
        String userName = resultSet.getString("user_name");
        String password = resultSet.getString("password");
        double balance = resultSet.getDouble("balance");
        User user = User.UserBuilder.anUser()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setNationalCode(nationalCode)
                .setPhoneNumber(phoneNumber)
                .setBirthDate(birthDate)
                .setEmail(email)
                .setUserName(userName)
                .setPassword(password)
                .build();
        return user;
    }


    public User findUserByUserNameAndPassword(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.userName=:username and u.password=:password");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        List<User> results = query.getResultList();
        transaction.commit();
        session.close();
        return results.get(0);
    }

    public boolean checkDuplicatedUser(String userName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.userName=:username");
        query.setParameter("username", userName);
        List<User> results = query.getResultList();
        transaction.commit();
        session.close();
        if (results.isEmpty()) {
            return false;
        } else return true;
    }


    /*
     * 1- check cart product count < 5
     * 2- add product to cart
     * 3- product count cart -> update
     * 4- total price cart -> update
     *
     * */
    public void addToCart(int userId, Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        Cart cart = session.get(Cart.class, user.getCart().getId());
        if (user != null && cart != null) {
            if (cart.getProductsCount() < maxSizeCart) {
                if (product.getCount() > 0) {
                    int index = findIndexProductInCart(product, cart);
                    if (index == -1) {
                        cart.getProductList().add(product);
                    }
                    updateCart(session, cart, cart.getProductsCount() + 1, cart.getTotalPrice() + product.getPrice());
                    transaction.commit();
                    session.close();
                } else throw new RuntimeException("product count is not enough!");
            } else throw new RuntimeException("the cart reached max size!");
        } else throw new RuntimeException("cannot find user!");

    }


    public void removeFromCart(int userId, Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        Cart cart = session.get(Cart.class, user.getCart().getId());
        if (user != null && cart != null) {
            int index = findIndexProductInCart(product, cart);
            if (index != -1) {
                if (cart.getProductsCount() == 1) {
                    cart.getProductList().remove(index);
                }
                updateCart(session, cart, cart.getProductsCount() - 1, cart.getTotalPrice() - product.getPrice());
            }
            transaction.commit();
            session.close();
        } else throw new RuntimeException("cannot find user!");

    }

    private void updateCart(Session session, Cart cart, int i, double v) {
        int newProductCount = i;
        double totalPrice = v;
        cart.setProductsCount(newProductCount);
        cart.setTotalPrice(totalPrice);
        session.update(cart);
    }

    private int findIndexProductInCart(Product product, Cart cart) {
        return IntStream.range(0, cart.getProductList().size())
                .filter(i -> cart.getProductList().get(i).getId() == product.getId())
                .findFirst().orElse(-1);
    }


}
