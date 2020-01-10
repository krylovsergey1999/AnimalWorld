package ru.animal.world.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "message_time", nullable = false)
  private LocalDateTime messageTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "messages")
  private Dialog dialog;
}