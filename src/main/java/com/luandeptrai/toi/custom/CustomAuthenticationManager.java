//package com.luandeptrai.toi.custom;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationManager implements AuthenticationManager  {
//
//  @Autowired
//  @Qualifier("userDetailsService")
//  private UserDetailsService userDetailsService;
//
//  @Autowired
//  private PasswordEncoder passwordEncoder;
//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
//    customAuthenticationProvider.setUserDetailsService(userDetailsService);
//    customAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//    return customAuthenticationProvider.authenticate(authentication);
//  }
//}
