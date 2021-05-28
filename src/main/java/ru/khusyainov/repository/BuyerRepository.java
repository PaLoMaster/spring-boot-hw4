package ru.khusyainov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khusyainov.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
