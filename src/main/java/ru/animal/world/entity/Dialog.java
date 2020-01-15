package ru.animal.world.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
