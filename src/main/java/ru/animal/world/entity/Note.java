package ru.animal.world.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "note")
@AllArgsConstructor
@NoArgsConstructor
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long noteId;

  @Column(name = "note_name", nullable = false)
  private String noteName;
}
