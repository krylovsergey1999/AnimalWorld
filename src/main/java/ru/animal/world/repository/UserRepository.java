package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}