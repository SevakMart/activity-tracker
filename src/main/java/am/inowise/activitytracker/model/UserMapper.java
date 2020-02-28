package am.inowise.activitytracker.model;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public UserMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto toDto(User user) {

    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setName(user.getName());
    userDto.setLastName(user.getLastName());
    userDto.setId(user.getId());
    userDto.setActive(user.getActive());
    return userDto;
  }

  public User toEntity(UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setActive(1);
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setName(userDto.getName());
    user.setLastName(userDto.getLastName());
    return user;
  }
}
