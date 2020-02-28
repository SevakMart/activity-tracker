package am.inowise.activitytracker.service.user;

import am.inowise.activitytracker.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> findUserByEmail(String name);

  User save(User user);

  List<User> findAll();

  Optional<User> findById(Long userId);
}
