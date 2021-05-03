package ru.khusyainov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.khusyainov.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCostBetween(Integer minCost, Integer maxCost);
    List<Product> findByCostLessThan(Integer maxCost);
    List<Product> findByCostGreaterThan(Integer minCost);
    Page<Product> findByCostBetween(Integer minCost, Integer maxCost, Pageable pageable);
    Page<Product> findByCostLessThan(Integer maxCost, Pageable pageable);
    Page<Product> findByCostGreaterThan(Integer minCost, Pageable pageable);
}
