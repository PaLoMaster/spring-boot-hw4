package ru.khusyainov.controller;

import org.springframework.stereotype.Repository;
import ru.khusyainov.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private Map<Integer, Product> list = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        if (product.getId() == 0 && !list.isEmpty()) product.setId(list.size());
        list.put(product.getId(), product);
    }

    @Override
    public List<Product> getProductsList() {
        return new ArrayList<>(list.values());
    }
}
