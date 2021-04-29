package ru.khusyainov.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.khusyainov.HibernateSessionFactory;
import ru.khusyainov.model.Buyer;

import java.util.List;

@Repository
public class BuyerRepositoryImpl implements BuyerRepository {
    private HibernateSessionFactory sessionFactory;

    @Autowired
    public BuyerRepositoryImpl(HibernateSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBuyer(Buyer buyer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(buyer);
        session.getTransaction().commit();
    }

    @Override
    public Buyer findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Buyer result = session.get(Buyer.class, id);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<Buyer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Buyer> result = session.createQuery("select a from Buyer a", Buyer.class).getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Buyer toRemove = session.get(Buyer.class, id);
        if (toRemove != null) session.remove(toRemove);
        session.getTransaction().commit();
    }

    @Override
    public Buyer saveOrUpdate(Buyer buyer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (buyer != null) session.saveOrUpdate(buyer);
        session.getTransaction().commit();
        return buyer;
    }
}
