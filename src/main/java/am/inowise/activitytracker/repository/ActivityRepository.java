package am.inowise.activitytracker.repository;

import am.inowise.activitytracker.model.domain.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<UserActivity, Long> {
  List<UserActivity> findByDate(LocalDate parsedDate);
}
