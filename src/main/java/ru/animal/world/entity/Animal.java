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
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long animalId;

  @Column(name = "animal_name", nullable = false)
  private String animalName;
}