package am.inowise.activitytracker.controller;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.dto.SelectDateDto;
import am.inowise.activitytracker.model.dto.UserActivityDto;
import am.inowise.activitytracker.model.dto.UserDto;
import am.inowise.activitytracker.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

  private final UserService userService;

  public ViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = {"/", "/login"})
  public String index() {
    return "login";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String admin(Model model) {
    model.addAttribute("userDto", new UserDto());
    return "admin/register";
  }

  @GetMapping("/admin/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String usersList(Model model) {
    List<User> allUsers = userService.findAll();
    model.addAttribute("users", allUsers);
    return "admin/usersList";
  }

  @GetMapping("/admin/activity")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String usersActivity(Model model) {
    model.addAttribute("selectDateDto", new SelectDateDto());
    return "admin/usersActivity";
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('USER')")
  public String user(Model model, Authentication authentication) {
    UserActivityDto activityDto = new UserActivityDto();
    activityDto.setUserEmail(authentication.getName());
    model.addAttribute("userActivityDto", activityDto);
    return "user/user";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "access-denied";
  }
}
