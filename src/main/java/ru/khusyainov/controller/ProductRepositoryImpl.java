package ru.khusyainov.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.khusyainov.model.Product;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Product.class).buildSessionFactory();
    private Session session;

    @Override
    public void addProduct(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    @Override
    public Product findById(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product result = session.get(Product.class, id);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<Product> findAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> result = session.createQuery("select a from Product a", Product.class).getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteById(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product toRemove = session.get(Product.class, id);
        if (toRemove != null) session.remove(toRemove);
        session.getTransaction().commit();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (product != null) session.saveOrUpdate(product);
        session.getTransaction().commit();
        return product;
    }
}
