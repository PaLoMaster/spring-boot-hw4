package ru.khusyainov.controller;

import org.springframework.stereotype.Repository;
import ru.khusyainov.model.Product;

import java.util.List;

@Repository
public interface ProductRepository {
    void addProduct(Product product);
    List<Product> getProductsList();
}
