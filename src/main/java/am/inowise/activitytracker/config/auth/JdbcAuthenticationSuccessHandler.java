package am.inowise.activitytracker.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class JdbcAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private static final String ADMIN_PAGE = "/admin";
  private static final String USERS_PAGE = "/users";

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
      response.sendRedirect(ADMIN_PAGE);
    } else {
      response.sendRedirect(USERS_PAGE);
    }
  }
}
