package ru.khusyainov.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private int id;
    private List<ProductDto> products;

    public CartDto() {

    }

    public CartDto(int id, List<ProductDto> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", products=" + products + "}";
    }
}