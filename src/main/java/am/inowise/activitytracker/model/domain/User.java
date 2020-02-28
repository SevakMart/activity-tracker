package am.inowise.activitytracker.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "USER_ID")
  private Long id;

  @Column(name = "EMAIL")
  @Email(message = "*Please provide a valid Email")
  @NotEmpty(message = "*Please provide an email")
  private String email;

  @Column(name = "PASSWORD")
  @Length(min = 5, message = "*Your password must have at least 5 characters")
  @NotEmpty(message = "*Please provide your password")
  private String password;

  @Column(name = "NAME")
  @NotEmpty(message = "*Please provide your name")
  private String name;

  @Column(name = "LAST_NAME")
  @NotEmpty(message = "*Please provide your last name")
  private String lastName;

  @Column(name = "ACTIVE")
  private int active;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "USER_ROLE",
      joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  private Set<UserRole> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<UserActivity> activities = new HashSet<>();

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("email='" + email + "'")
        .add("name='" + name + "'")
        .add("lastName='" + lastName + "'")
        .toString();
  }
}
