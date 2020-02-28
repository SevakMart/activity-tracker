package am.inowise.activitytracker.service.user;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.domain.UserRole;
import am.inowise.activitytracker.repository.RoleRepository;
import am.inowise.activitytracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository rolesRepository;

  public UserServiceImpl(UserRepository userRepository, RoleRepository rolesRepository) {
    this.userRepository = userRepository;
    this.rolesRepository = rolesRepository;
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return Optional.ofNullable(userRepository.findByEmail(email));
  }

  @Override
  public User save(User user) {
    UserRole role = rolesRepository.findByRole("USER");
    user.getRoles().add(role);
    return userRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Long userId) {
    return userRepository.findById(userId);
  }
}
