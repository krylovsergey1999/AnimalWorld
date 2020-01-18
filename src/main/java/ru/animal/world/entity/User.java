package ru.animal.world.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;

@Data
@Builder
@Entity
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "user_name", nullable = false)
  private String username;

  @Column(name = "user_first_name", nullable = false)
  private String userFirstName;

  @Column(name = "user_last_name", nullable = false)
  private String userLastName;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

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

  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
  private Set<Note> notes;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private List<Dialog> dialogs;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
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