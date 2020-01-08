package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.Dialog;

public interface DialogRepository extends JpaRepository<Dialog, Long> {

}