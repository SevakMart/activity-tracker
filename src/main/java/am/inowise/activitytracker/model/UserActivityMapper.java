package am.inowise.activitytracker.model;

import am.inowise.activitytracker.model.domain.UserActivity;
import am.inowise.activitytracker.model.dto.UserActivityDto;
import org.springframework.stereotype.Component;

@Component
public class UserActivityMapper {

  public UserActivity toEntity(UserActivityDto dto) {
    UserActivity userActivity = new UserActivity();
    userActivity.setDescription(dto.getDescription());
    userActivity.setId(dto.getId());
    return userActivity;
  }
}
