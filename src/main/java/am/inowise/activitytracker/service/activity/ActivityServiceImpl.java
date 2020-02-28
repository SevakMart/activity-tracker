package am.inowise.activitytracker.service.activity;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.domain.UserActivity;
import am.inowise.activitytracker.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
  private final ActivityRepository activityRepository;

  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public void addActivity(UserActivity activity, User user) {

    activity.setDate(LocalDate.now());
    activity.setUser(user);
    activityRepository.save(activity);
  }

  @Override
  public List<UserActivity> findByDate(String date) {
    LocalDate parsedDate = LocalDate.parse(date);
    return activityRepository.findByDate(parsedDate);
  }
}
