package am.inowise.activitytracker.controller;

import am.inowise.activitytracker.model.UserMapper;
import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.dto.UserDto;
import am.inowise.activitytracker.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/registration")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ModelAndView registerUser(@Valid UserDto userDto, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    userService
        .findUserByEmail(userDto.getEmail())
        .ifPresent(
            u ->
                bindingResult.rejectValue(
                    "email",
                    "error.user",
                    "There is already a user registered with the email provided"));

    if (!bindingResult.hasErrors()) {
      userService.save(userMapper.toEntity(userDto));
      modelAndView.addObject("successMessage", "User has been registered successfully");
      modelAndView.addObject("userDto", new UserDto());
    }
    modelAndView.setViewName("admin/register");
    return modelAndView;
  }

  @PostMapping("/usersList")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ModelAndView getUsersList() {
    List<User> allUsers = userService.findAll();
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("admin/usersList");
    modelAndView.addObject("users", allUsers);
    return modelAndView;
  }
}
