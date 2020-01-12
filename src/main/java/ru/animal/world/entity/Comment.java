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
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

  @Column(name = "comment_text", nullable = false)
  private String commentText;

  @Column(name = "comment_time", nullable = false)
  private LocalDateTime commentTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "comment_id")
  private Note note;
}