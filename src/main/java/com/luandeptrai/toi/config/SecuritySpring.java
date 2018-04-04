package com.luandeptrai.toi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity( debug = true )
public class SecuritySpring extends WebSecurityConfigurerAdapter {
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
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication() // creating user in memory
        .withUser("user")
        .password("password").roles("USER")
        .and().withUser("admin")
        .password("password").authorities("ROLE_ADMIN");
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // provides the default AuthenticationManager as a Bean
    return super.authenticationManagerBean();
  }


}
