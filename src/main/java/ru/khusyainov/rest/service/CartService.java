package ru.khusyainov.rest.service;

import org.springframework.stereotype.Service;
import ru.khusyainov.model.Product;
import ru.khusyainov.repository.ProductRepository;
import ru.khusyainov.rest.dto.CartDto;
import ru.khusyainov.rest.dto.ProductDto;
import ru.khusyainov.model.Cart;
import ru.khusyainov.repository.CartRepository;
import ru.khusyainov.rest.mapper.CartConverter;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartDto> findAll() {
        return CartConverter.toDtoList(cartRepository.findAll());
    }

    public CartDto findOne(Integer id) {
        return CartConverter.toDto(cartRepository.getOne(id));
    }

    public CartDto findOneByBuyerId(Integer id) {
        return CartConverter.toDto(cartRepository.findByBuyer_Id(id));
    }

    public CartDto save(CartDto cartDto) {
        Cart cart = CartConverter.fromDto(cartDto);
        cart.setBuyer(cartRepository.getOne(cart.getId()).getBuyer());
        cart = cartRepository.save(cart);
        return CartConverter.toDto(cart);
    }

    public void delete(CartDto cartDto) {
        Cart cart = CartConverter.fromDto(cartDto);
        cart.clear();
        cartRepository.save(cart);
    }

    public void delete(Integer cartId) {
        Cart cart = cartRepository.getOne(cartId);
        cart.clear();
        cartRepository.save(cart);
    }

    public CartDto addProduct(CartDto cartDto, ProductDto productDto) {
        return addProduct(cartDto, productDto.getId());
    }

    public CartDto addProduct(CartDto cartDto, Integer productId) {
        Cart cart = CartConverter.fromDto(cartDto);
        Product product = productRepository.findById(productId).
                orElseThrow(() ->
                        new EntityNotFoundException("Unable to find Product with id " + productId));
        cart.addProduct(product);
        cart = cartRepository.save(cart);
        return CartConverter.toDto(cart);
    }

    public CartDto deleteProduct(CartDto cartDto, ProductDto productDto) {
        return deleteProduct(cartDto, productDto.getId());
    }

    public CartDto deleteProduct(CartDto cartDto, Integer productId) {
        Cart cart = CartConverter.fromDto(cartDto);
        Product product = productRepository.getOne(productId);
        cart.deleteProduct(product);
        cart = cartRepository.save(cart);
        return CartConverter.toDto(cart);
    }
}