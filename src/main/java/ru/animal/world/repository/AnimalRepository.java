package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}