package ru.khusyainov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khusyainov.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}