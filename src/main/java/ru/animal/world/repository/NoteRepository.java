package ru.animal.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animal.world.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}