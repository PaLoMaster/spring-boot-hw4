package ru.khusyainov;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.khusyainov.controller.BuyerRepository;
import ru.khusyainov.controller.ProductRepository;
import ru.khusyainov.controller.ProductsAndBuyersService;

@SpringBootApplication
public class Application implements CommandLineRunner {
    
    ProductRepository productRepository;
    BuyerRepository buyerRepository;
    ProductsAndBuyersService productsAndBuyersService;

    public Application(ProductRepository productRepository, BuyerRepository buyerRepository, ProductsAndBuyersService productsAndBuyersService) {
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
        this.productsAndBuyersService = productsAndBuyersService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("All products:");
        productRepository.findAll().forEach(System.out::println);
        System.out.println("\nAll buyers:");
        buyerRepository.findAll().forEach(System.out::println);
        int findId = 3;
        System.out.println("\nAll buyers of " + productRepository.findById(findId) + ":");
        productsAndBuyersService.getBuyersByProductId(findId).forEach(System.out::println);
        findId = 5;
        System.out.println("\nAll products of " + buyerRepository.findById(findId) + ":");
        productsAndBuyersService.getProductsByBuyerId(findId).forEach(System.out::println);
    }
}
