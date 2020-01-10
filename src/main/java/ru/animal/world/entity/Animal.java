package ru.animal.world.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long animalId;

  @Column(name = "animal_name", nullable = false)
  private String animalName;

  @Column(name = "cities")
  @Enumerated(EnumType.STRING)
  private Set<City> cities;

  @Column(name = "snapshot")
  private String snapshot;

  @Column(name = "description")
  private String description;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "date_of_birth")
  private LocalDateTime dateOfBirth;

  @ManyToMany(mappedBy = "animals")
  private Set<Note> notes;
}