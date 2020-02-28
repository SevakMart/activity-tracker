package am.inowise.activitytracker.repository;

import am.inowise.activitytracker.model.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
  UserRole findByRole(String role);
}
