package ru.animal.world.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "note")
@AllArgsConstructor
@NoArgsConstructor
public class Note implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long noteId;

  @Column(name = "note_name", nullable = false)
  private String noteName;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime createDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;

  @ManyToMany(mappedBy = "notes", fetch = FetchType.EAGER)
  private Set<Animal> animals;

}
