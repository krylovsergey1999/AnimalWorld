package ru.animal.world.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "dialog")
@AllArgsConstructor
@NoArgsConstructor
public class Dialog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diID;
}