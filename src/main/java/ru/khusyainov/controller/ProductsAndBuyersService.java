package ru.khusyainov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khusyainov.model.Buyer;
import ru.khusyainov.model.Product;

import java.util.List;

@Service
public class ProductsAndBuyersService {

    private ProductRepository productRepository;

    private BuyerRepository buyerRepository;

    @Autowired
    public ProductsAndBuyersService(ProductRepository productRepository, BuyerRepository buyerRepository) {
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
    }

    public List<Product> getProductsByBuyerId(Integer id) {
        return buyerRepository.findById(id).getProducts();
    }

    public List<Buyer> getBuyersByProductId(Integer id) {
        return productRepository.findById(id).getBuyers();
    }
}