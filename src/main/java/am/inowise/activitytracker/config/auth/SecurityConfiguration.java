package am.inowise.activitytracker.config.auth;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;
  private final AuthenticationSuccessHandler successHandler;
  private final AccessDeniedHandler accessDeniedHandler;
  private final String usersQuery;
  private final String rolesQuery;

  public SecurityConfiguration(
      DataSource dataSource,
      AuthenticationSuccessHandler successHandler,
      AccessDeniedHandler accessDeniedHandler,
      @Value("${spring.queries.users-query}") String usersQuery,
      @Value("${spring.queries.roles-query}") String rolesQuery) {
    this.dataSource = dataSource;
    this.successHandler = successHandler;
    this.accessDeniedHandler = accessDeniedHandler;
    this.usersQuery = usersQuery;
    this.rolesQuery = rolesQuery;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder())
        .usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(rolesQuery);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/", "/login")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error=true")
        .successHandler(successHandler)
        .usernameParameter("email")
        .passwordParameter("password")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler);
  }

  @Bean
  ServletRegistrationBean<WebServlet> h2servletRegistration() {
    ServletRegistrationBean<WebServlet> registrationBean =
        new ServletRegistrationBean<>(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }

  @Override
  public void configure(WebSecurity web) {

    web.ignoring()
        .antMatchers(
            "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/console/**");
  }
}
