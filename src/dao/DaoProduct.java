package dao;

import model.Product;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;


public class DaoProduct extends Dao {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public List<Product> findAllProducts() {
        List<Product> productList;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        productList = session.createQuery("from Product ", Product.class).list();
        transaction.commit();
        session.close();
        return productList;
    }

    public Product findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class,id);
        transaction.commit();
        session.close();
        if(product == null)
            throw new RuntimeException("cannot find product!");
        return product;
    }

    public void updateProductCount(int count,int productId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class,productId);
        int newCount = product.getCount()+count;
        product.setCount(newCount);
        session.update(product);
        transaction.commit();
        session.close();
    }

    public void save(Product product){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

}
