package ru.khusyainov.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khusyainov.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCostBetween(Integer minCost, Integer maxCost);
    List<Product> findByCostLessThan(Integer maxCost);
    List<Product> findByCostGreaterThan(Integer minCost);
}
