package ru.khusyainov.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return getAllFiltered(null, null);
    }

    @GetMapping(value = "/filter")
    public List<ProductDto> getAllFiltered(@RequestParam Integer minCost,
                                           @RequestParam Integer maxCost) {
        List<ProductDto> products;
        if (minCost == null && maxCost == null) {
            products = productService.findAll();
        } else if (minCost != null && maxCost != null) {
            products = productService.findByCostBetween(minCost, maxCost);
        } else if (minCost != null) {
            products = productService.findByCostGreaterThan(minCost);
        } else {
            products = productService.findByCostLessThan(maxCost);
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