package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Status;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
    implements Serializable {


  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "user_last_name", nullable = false)
  private String userLastName;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "date_of_birth")
  private LocalDateTime dateOfBirth;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "city", nullable = false)
  @Enumerated(EnumType.STRING)
  private City city;

  @Column(name = "snapshot")
  private String snapshot;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "created_on", nullable = false, updatable = false)
  private LocalDateTime createdOn;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "author_note")
  private Set<Note> notes;

  @ManyToMany(mappedBy = "users_dialog")
  private List<Dialog> dialogs;

  @OneToMany(mappedBy = "users_animal")
  private Set<Animal> animals;

  public User(Long id, String userName, String userLastName, Gender gender, LocalDateTime dateOfBirth, String email,
      String password, City city, String snapshot, String description, Status status, LocalDateTime createdOn,
      LocalDateTime lastLogin) {
    this.id = id;
    this.userName = userName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.status = status;
    this.snapshot = snapshot;
    this.createdOn = createdOn;
    this.lastLogin = lastLogin;
  }
}