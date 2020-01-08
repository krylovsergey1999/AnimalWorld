package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}