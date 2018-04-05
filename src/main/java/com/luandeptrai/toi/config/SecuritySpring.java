package com.luandeptrai.toi.config;

import com.luandeptrai.toi.service.UserServices;
import com.luandeptrai.toi.service.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity( debug = true )
public class SecuritySpring extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserServices userServices;

  @Autowired
  private PasswordEncoder passwordEncoder;

  protected void configure(HttpSecurity http) throws Exception {
    http
        .formLogin().disable() // disable form authentication
        .anonymous().disable() // disable anonymous user
        .csrf().disable()
        .mvcMatcher("/funny/*")
        .mvcMatcher("/user/")
        .authorizeRequests()
        .anyRequest().denyAll(); // denying all access
  }

  @Override
  public void configure(WebSecurity web) {
    web.debug(true).ignoring().mvcMatchers("/funny/*").mvcMatchers("/user/");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider
        = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return new UserServicesImpl();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // provides the default AuthenticationManager as a Bean
    return super.authenticationManagerBean();
  }


}
