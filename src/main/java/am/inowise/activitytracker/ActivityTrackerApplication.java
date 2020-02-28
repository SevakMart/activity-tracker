package am.inowise.activitytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActivityTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityTrackerApplication.class, args);
	}

}
