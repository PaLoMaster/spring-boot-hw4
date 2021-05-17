package ru.khusyainov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khusyainov.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}