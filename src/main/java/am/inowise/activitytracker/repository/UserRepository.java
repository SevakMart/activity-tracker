package am.inowise.activitytracker.repository;

import am.inowise.activitytracker.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query(
      value =
          "SELECT *"
              + " FROM users u"
              + " left JOIN activities a"
              + " ON u.user_id = a.USER_USER_ID"
              + " inner join user_role ur on(u.user_id=ur.user_id) inner join roles r on(ur.role_id=r.role_id)"
              + " where not a.SUBMIT_DATE = ?1 or a.SUBMIT_DATE is null and not r.role = 'ADMIN'",
      nativeQuery = true)
  List<User> findUsersWithoutActivity(LocalDate localDate);

}
