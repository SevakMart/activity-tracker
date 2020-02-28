package am.inowise.activitytracker.service.notification;

import org.springframework.stereotype.Component;

@Component("sms")
public class SmsService implements NotificationService {
  @Override
  public void send(String to, String subject, String text) {
    throw new UnsupportedOperationException("The feature will be available soon !!!");
  }
}
