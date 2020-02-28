package am.inowise.activitytracker.service.notification;

public interface NotificationService {
  void send(String to, String subject, String text);
}
