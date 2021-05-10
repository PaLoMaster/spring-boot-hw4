package ru.khusyainov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khusyainov.rest.dto.ProductDto;
import ru.khusyainov.rest.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private ProductService productService;
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll(@RequestParam(required = false) Integer minCost,
                                   @RequestParam(required = false) Integer maxCost,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER + "") int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize) {
        List<ProductDto> products;
        Pageable pageable = PageRequest.of(page, pageSize);
        if (minCost == null && maxCost == null) {
            products = productService.findAll(pageable);
        } else if (minCost != null && maxCost != null) {
            products = productService.findByCostBetween(minCost, maxCost, pageable);
        } else if (minCost != null) {
            products = productService.findByCostGreaterThan(minCost, pageable);
        } else {
            products = productService.findByCostLessThan(maxCost, pageable);
        }
        return products;
    }

    @PostMapping
    public ProductDto add(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto update(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @GetMapping(value = "/{id}")
    public ProductDto get(@PathVariable Integer id) {
        return productService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable Integer id) {
        productService.delete(id);
        return HttpStatus.OK.value();
    }
}