package am.inowise.activitytracker.controller;

import am.inowise.activitytracker.model.UserActivityMapper;
import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.model.domain.UserActivity;
import am.inowise.activitytracker.model.dto.SelectDateDto;
import am.inowise.activitytracker.model.dto.UserActivityDto;
import am.inowise.activitytracker.service.activity.ActivityService;
import am.inowise.activitytracker.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ActivityController {

  private final ActivityService activityService;
  private final UserService userService;
  private final UserActivityMapper activityMapper;

  public ActivityController(
      ActivityService activityService, UserService userService, UserActivityMapper activityMapper) {
    this.activityService = activityService;
    this.userService = userService;
    this.activityMapper = activityMapper;
  }

  @PostMapping("/activity")
  @PreAuthorize("hasAuthority('USER')")
  public ModelAndView submitActivity(
      @Valid UserActivityDto userActivityDto, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();

    Optional<User> user = userService.findUserByEmail(userActivityDto.getUserEmail());
    if (!user.isPresent()) {
      bindingResult.rejectValue(
          "userEmail", "error.userEmail", "There is no user with the provided email");
    }

    if (!bindingResult.hasErrors()) {
      activityService.addActivity(activityMapper.toEntity(userActivityDto), user.get());
      modelAndView.addObject("successMessage", "Activity has been submitted successfully");
      UserActivityDto activity = new UserActivityDto();
      activity.setUserEmail(userActivityDto.getUserEmail());
      modelAndView.addObject("userActivityDto", activity);
    }
    modelAndView.setViewName("user/user");
    return modelAndView;
  }

  @PostMapping("/activities")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ModelAndView submitActivity(
      @Valid SelectDateDto selectDateDto, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();

    List<UserActivity> activities = activityService.findByDate(selectDateDto.getDate());
    if (!bindingResult.hasErrors()) {
      modelAndView.addObject("activities", activities);
      modelAndView.addObject("selectDateDto", new SelectDateDto());
    }
    modelAndView.setViewName("admin/usersActivity");
    return modelAndView;
  }
}
