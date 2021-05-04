package ru.khusyainov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khusyainov.repository.ProductRepository;
import ru.khusyainov.model.Product;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsRestController {
    private ProductRepository productRepository;
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) Integer minCost,
                                         @RequestParam(required = false) Integer maxCost,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER + "") int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize) {
        Page<Product> productsPage;
        Pageable pageable = PageRequest.of(page, pageSize);
        if (minCost == null && maxCost == null) {
            productsPage = productRepository.findAll(pageable);
        } else if (minCost != null && maxCost != null) {
            productsPage = productRepository.findByCostBetween(minCost, maxCost, pageable);
        } else if (minCost != null) {
            productsPage = productRepository.findByCostGreaterThan(minCost, pageable);
        } else {
            productsPage = productRepository.findByCostLessThan(maxCost, pageable);
        }
        return productsPage.getContent();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productRepository.findById(id).get();
    }

    @DeleteMapping(value = "/{id}")
    public int deleteProductById(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return HttpStatus.OK.value();
    }
}