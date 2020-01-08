package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}