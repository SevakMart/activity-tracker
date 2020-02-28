package am.inowise.activitytracker.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACTIVITIES")
public class UserActivity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACTIVITY_ID")
  private Long id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "SUBMIT_DATE")
  private LocalDate date;

  @ManyToOne @JoinColumn private User user;
}
