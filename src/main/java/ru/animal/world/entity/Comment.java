package ru.animal.world.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(name = "comment_text", nullable = false)
  private String commentText;

  @Column(name = "comment_time", nullable = false)
  private LocalDateTime commentTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "comments")
  private Note note;
}
