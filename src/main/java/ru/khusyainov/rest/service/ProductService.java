package ru.khusyainov.rest.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.khusyainov.rest.dto.ProductDto;
import ru.khusyainov.model.Product;
import ru.khusyainov.repository.ProductRepository;
import ru.khusyainov.rest.mapper.ProductConverter;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        return ProductConverter.toDtoList(productRepository.findAll());
    }

    public List<ProductDto> findAll(Pageable pageable) {
        return ProductConverter.toDtoList(productRepository.findAll(pageable).getContent());
    }

    public ProductDto findOne(Integer id) {
        return ProductConverter.toDto(productRepository.getOne(id));
    }

    public ProductDto save(ProductDto productDto) {
        Product product = ProductConverter.fromDto(productDto);
        product = productRepository.save(product);
        return ProductConverter.toDto(product);
    }

    public void delete(ProductDto productDto) {
        Product product = ProductConverter.fromDto(productDto);
        productRepository.delete(product);
    }

    public void delete(Integer productId) {
        Product product = productRepository.getOne(productId);
        productRepository.delete(product);
    }

    public List<ProductDto> findByCostBetween(Integer minCost, Integer maxCost) {
        return ProductConverter.toDtoList(productRepository.findByCostBetween(minCost, maxCost));
    }

    public List<ProductDto> findByCostLessThan(Integer maxCost) {
        return ProductConverter.toDtoList(productRepository.findByCostLessThan(maxCost));
    }

    public List<ProductDto> findByCostGreaterThan(Integer minCost) {
        return ProductConverter.toDtoList(productRepository.findByCostGreaterThan(minCost));
    }
}