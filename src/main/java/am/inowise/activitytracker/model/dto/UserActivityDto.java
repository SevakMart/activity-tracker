package am.inowise.activitytracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityDto {

  private Long id;

  @NotEmpty(message = "*Please provide a description")
  private String description;

  private LocalDate date;

  private String userEmail;
}
