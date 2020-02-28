package am.inowise.activitytracker.service;

import am.inowise.activitytracker.model.domain.User;
import am.inowise.activitytracker.repository.UserRepository;
import am.inowise.activitytracker.service.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class Notifier {
  private final NotificationService notificationService;
  private final UserRepository userRepository;
  private final String subject;
  private final String text;

  public Notifier(
      @Qualifier("email") NotificationService notificationService,
      UserRepository userRepository,
      @Value("${notification.subject}") String subject,
      @Value("${notification.text}") String text) {
    this.notificationService = notificationService;
    this.userRepository = userRepository;
    this.subject = subject;
    this.text = text;
  }

  @Scheduled(cron = "${notification.frequency}")
  public void sendNotification() {
    List<User> usersWithoutActivity = userRepository.findUsersWithoutActivity(LocalDate.now());
    log.info("Sending notifications to users: {}", usersWithoutActivity);
    usersWithoutActivity.forEach(user -> notificationService.send(user.getEmail(), subject, text));
  }
}
