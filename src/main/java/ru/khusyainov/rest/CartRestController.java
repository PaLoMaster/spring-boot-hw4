package ru.khusyainov.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khusyainov.rest.dto.CartDto;
import ru.khusyainov.rest.dto.ProductDto;
import ru.khusyainov.rest.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @GetMapping(value = "/{id}")
    public CartDto get(@PathVariable Integer id) {
        return cartService.findOne(id);
    }

    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CartDto addProduct(@PathVariable Integer id,
                           @RequestBody ProductDto product) {
        CartDto cart = cartService.findOne(id);
        cart = cartService.addProduct(cart, product);
        return cart;
    }

    @PostMapping(value = "/{id}/{productId}")
    public CartDto addProductById(@PathVariable Integer id,
                           @PathVariable Integer productId) {
        CartDto cart = cartService.findOne(id);
        cart = cartService.addProduct(cart, productId);
        return cart;
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CartDto update(@RequestBody CartDto cart) {
        return cartService.save(cart);
    }

    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable Integer id) {
        cartService.delete(id);
        return HttpStatus.OK.value();
    }
}