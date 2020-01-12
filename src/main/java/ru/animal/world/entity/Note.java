package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "note")
@AllArgsConstructor
@NoArgsConstructor
public class Note extends BaseEntity implements Serializable {

  @Column(name = "note_name", nullable = false)
  private String noteName;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime createDate;

  @OneToMany(mappedBy = "note", fetch = FetchType.EAGER)
  private Set<Comment> comments;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author_note;

  @ManyToMany()
  @JoinTable(
      name = "animal_note",
      joinColumns = {@JoinColumn(name = "note_id")},
      inverseJoinColumns = {@JoinColumn(name = "animal_id")}
  )
  private Set<Animal> animals_note;

  public Note(Long id, String noteName, String description, LocalDateTime createDate) {
    this.id = id;
    this.noteName = noteName;
    this.description = description;
    this.createDate = createDate;
  }
}