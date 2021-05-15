package ru.khusyainov.rest.mapper;

import ru.khusyainov.model.Cart;
import ru.khusyainov.rest.dto.CartDto;

import java.util.ArrayList;
import java.util.List;

public class CartConverter {

    public static Cart fromDto(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }
        return new Cart(cartDto.getId(), null, ProductConverter.fromDtoList(cartDto.getProducts()));
    }

    public static CartDto toDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        return new CartDto(cart.getId(), ProductConverter.toDtoList(cart.getProducts()));
    }

    public static List<Cart> fromDtoList(List<CartDto> cartDtos) {
        if (cartDtos == null) {
            return null;
        }
        List<Cart> list = new ArrayList<>(cartDtos.size());
        cartDtos.forEach(cartDto -> list.add(fromDto(cartDto)));
        return list;
    }

    public static List<CartDto> toDtoList(List<Cart> carts) {
        if (carts == null) {
            return null;
        }
        List<CartDto> list = new ArrayList<>(carts.size());
        carts.forEach(cart -> list.add(toDto(cart)));
        return list;
    }
}