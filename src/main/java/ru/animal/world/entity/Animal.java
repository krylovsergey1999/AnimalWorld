package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends BaseEntity implements Serializable {

  @Column(name = "animal_name", nullable = false)
  private String animalName;

  @Column(name = "city")
  @Enumerated(EnumType.STRING)
  private City city;

//  @Column(name = "city_id")
//  @ElementCollection(targetClass = City.class)
//  @CollectionTable(name = "tbl_animal_cities", joinColumns = @JoinColumn(name = "animal_id"))
//  private Set<City> cities;

  @Column(name = "snapshot")
  private String snapshot;

  @Column(name = "description")
  private String description;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "date_of_birth")
  private LocalDateTime dateOfBirth;

  @ManyToMany(mappedBy = "animalsNote", fetch = FetchType.EAGER)
  private Set<Note> notesAnimal;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude
  private User usersAnimal;

  public Animal(Long id, String animalName, City city, String snapshot, String description, Gender gender,
      LocalDateTime dateOfBirth) {
    this.id = id;
    this.animalName = animalName;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
  }
}