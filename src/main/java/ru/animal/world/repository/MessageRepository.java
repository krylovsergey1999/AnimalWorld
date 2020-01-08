package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}