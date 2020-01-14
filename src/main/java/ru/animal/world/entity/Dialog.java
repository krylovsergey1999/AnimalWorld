package ru.animal.world.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "dialog")
@AllArgsConstructor
@NoArgsConstructor
public class Dialog implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dialogId;

  @Column(name = "text_body")
  private String textBody;

  @OneToMany(mappedBy = "dialog", fetch = FetchType.EAGER)
  private Set<Message> messages;

  @ManyToMany()
  @JoinTable(
          name = "user_dialog",
          joinColumns = { @JoinColumn(name = "dialog_id") },
          inverseJoinColumns = { @JoinColumn(name = "user_id") }
  )
  private Set<User> users;
}