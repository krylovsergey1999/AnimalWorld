package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.animal.world.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);

    User findByEmail(String email);
}