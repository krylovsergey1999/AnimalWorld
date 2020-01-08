package ru.animal.world.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
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
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userDetailsId;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "user_last_name", nullable = false)
  private String userLastName;

  @Column(name = "created_on", nullable = false)
  private LocalDateTime created_on;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;
}