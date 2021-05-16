package ru.khusyainov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khusyainov.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByBuyer_Id(Integer buyerId);
}