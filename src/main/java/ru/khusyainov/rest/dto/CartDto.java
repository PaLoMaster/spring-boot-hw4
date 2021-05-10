package ru.khusyainov.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private int id;
    private BuyerDto buyer;
    private List<ProductDto> products;

    public CartDto() {

    }

    public CartDto(int id, BuyerDto buyer) {
        this.id = id;
        this.buyer = buyer;
        this.products = new ArrayList<>();
    }

    public CartDto(int id, BuyerDto buyer, List<ProductDto> products) {
        this.id = id;
        this.buyer = buyer;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BuyerDto getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDto buyer) {
        this.buyer = buyer;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", buyer=" + buyer + ", products=" + products + "}";
    }
}