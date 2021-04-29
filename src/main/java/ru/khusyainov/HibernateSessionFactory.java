package ru.khusyainov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.khusyainov.model.Buyer;
import ru.khusyainov.model.Product;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HibernateSessionFactory {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init () {
        sessionFactory = new Configuration().
                            addAnnotatedClass(Product.class).
                            addAnnotatedClass(Buyer.class).
                            buildSessionFactory();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @PreDestroy
    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}