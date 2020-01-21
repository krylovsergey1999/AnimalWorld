package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
    implements Serializable, UserDetails {


  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "user_first_name", nullable = false)
  private String userFirstName;

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

  @Column(name = "active")
  private Boolean active;

  @Column(name = "created_on", nullable = false, updatable = false)
  private LocalDateTime createdOn;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "authorNote", fetch = FetchType.EAGER)
  private Set<Note> notes;

  @ManyToMany(mappedBy = "usersDialog", fetch = FetchType.EAGER)
  private List<Dialog> dialogs;

  @OneToMany(mappedBy = "usersAnimal", fetch = FetchType.EAGER)
  private Set<Animal> animals;

  public User(Long id, String username, String userFirstName, String userLastName, Gender gender, LocalDateTime dateOfBirth, String email,
      String password, City city, String snapshot, String description, Status status, boolean active, LocalDateTime createdOn,
      LocalDateTime lastLogin, Role role) {
    this.id = id;
    this.userName = username;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.status = status;
    this.active = active;
    this.snapshot = snapshot;
    this.createdOn = createdOn;
    this.lastLogin = lastLogin;
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(getRole());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return getActive();
  }
}