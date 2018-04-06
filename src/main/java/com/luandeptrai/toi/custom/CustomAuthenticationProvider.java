//package com.luandeptrai.toi.custom;
//
//import com.luandeptrai.toi.models.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//  private UserDetailsService userDetailsService;
//
//  private PasswordEncoder passwordEncoder;
//
//  public CustomAuthenticationProvider() {
//  }
//
//  public UserDetailsService getUserDetailsService() {
//    return userDetailsService;
//  }
//
//  public void setUserDetailsService(UserDetailsService userDetailsService) {
//    this.userDetailsService = userDetailsService;
//  }
//
//  public PasswordEncoder getPasswordEncoder() {
//    return passwordEncoder;
//  }
//
//  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//    String username = authentication.getName().trim();
//    String password = authentication.getCredentials().toString().trim();
//
//    UserDetails userEntity = this.userDetailsService.loadUserByUsername(username);
//
//    if(passwordEncoder.matches(password, userEntity.getPassword())) {
//      Authentication auth;
//
//      if(userEntity instanceof CurrentUser) {
//        auth = new UsernamePasswordAuthenticationToken(userEntity, password, userEntity.getAuthorities());
//      } else {
//        auth = new UsernamePasswordAuthenticationToken(username, password, userEntity.getAuthorities());
//      }
//      return  auth;
//    } else {
//      throw new BadCredentialsException("Bad credentials");
//    }
//
//
//
//  }
//
//  @Override
//  public boolean supports(Class<?> aClass) {
//    return aClass.equals(UsernamePasswordAuthenticationToken.class);
//  }
//}
