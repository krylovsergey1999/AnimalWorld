package ru.animal.world.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "dialog")
@AllArgsConstructor
@NoArgsConstructor
public class Dialog extends BaseEntity implements Serializable {

  @Column(name = "text_body")
  private String textBody;

  @OneToMany(mappedBy = "dialog", fetch = FetchType.EAGER)
  private Set<Message> messages;

  @ManyToMany()
  @JoinTable(
      name = "user_dialog",
      joinColumns = {@JoinColumn(name = "dialog_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  @EqualsAndHashCode.Exclude
  private Set<User> usersDialog;

  public Dialog(Long id, String textBody) {
    this.id = id;
    this.textBody = textBody;
  }
}