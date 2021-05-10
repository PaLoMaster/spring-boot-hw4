package ru.khusyainov.rest.dto;

public class BuyerDto {

    private int id;
    private String name;
    private CartDto cart;

    public BuyerDto() {
    }

    public BuyerDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BuyerDto(int id, String name, CartDto cartDto) {
        this.id = id;
        this.name = name;
        this.cart = cartDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Buyer{" + "id=" + id + ", name='" + name + "', cart=" + cart + "}";
    }
}