package am.inowise.activitytracker.service.activity;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.domain.UserActivity;

import java.util.List;

public interface ActivityService {

  void addActivity(UserActivity toEntity, User id);

  List<UserActivity> findByDate(String date);
}
