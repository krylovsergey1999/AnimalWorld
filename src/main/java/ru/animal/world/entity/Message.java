package ru.animal.world.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "message_time", nullable = false)
  private LocalDateTime messageTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "message_id")
  @EqualsAndHashCode.Exclude
  private Dialog dialog;
}