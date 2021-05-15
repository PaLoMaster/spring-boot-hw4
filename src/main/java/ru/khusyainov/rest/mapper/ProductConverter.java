package ru.khusyainov.rest.mapper;

import ru.khusyainov.model.Product;
import ru.khusyainov.rest.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {

    public static Product fromDto(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

    public static List<Product> fromDtoList(List<ProductDto> productDtos) {
        if (productDtos == null) {
            return null;
        }
        List<Product> list = new ArrayList<>(productDtos.size());
        productDtos.forEach(productDto -> list.add(fromDto(productDto)));
        return list;
    }

    public static List<ProductDto> toDtoList(List<Product> products) {
        if (products == null) {
            return null;
        }
        List<ProductDto> list = new ArrayList<>(products.size());
        products.forEach(product -> list.add(toDto(product)));
        return list;
    }
}