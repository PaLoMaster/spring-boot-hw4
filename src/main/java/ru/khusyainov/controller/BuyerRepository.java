package ru.khusyainov.controller;

import org.springframework.stereotype.Repository;
import ru.khusyainov.model.Buyer;

import java.util.List;

@Repository
public interface BuyerRepository {
    void addBuyer(Buyer product);
    Buyer findById(int id);
    List<Buyer> findAll();
    void deleteById(int id);
    Buyer saveOrUpdate(Buyer product);
}
