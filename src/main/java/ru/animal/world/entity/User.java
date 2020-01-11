package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Status;

@Data
@Builder
@Entity
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

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

  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private Set<Note> notes;

  @ManyToMany(mappedBy = "users")
  private List<Dialog> dialogs;
}