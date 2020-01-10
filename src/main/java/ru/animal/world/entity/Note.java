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

  @OneToMany(mappedBy = "note", fetch = FetchType.EAGER)
  private Set<Comment> comments;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "notes")
  private User author;

  @ManyToMany()
  @JoinTable(
          name = "Animal_note",
          joinColumns = { @JoinColumn(name = "note_id") },
          inverseJoinColumns = { @JoinColumn(name = "animal_id") }
  )
  private Set<Animal> animals;

}
