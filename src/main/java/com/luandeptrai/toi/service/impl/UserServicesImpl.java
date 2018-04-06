package com.luandeptrai.toi.service.impl;

import com.luandeptrai.toi.models.UserEntity;
import com.luandeptrai.toi.repositories.UserRepository;
import com.luandeptrai.toi.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsService")
public class UserServicesImpl implements UserServices {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServicesImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public UserEntity save(UserEntity entity) {
    entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
    entity.setEnable(true);
    entity.setDeleted(false);
    return this.userRepository.save(entity);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsernameAndIsDeleted(s, false);
    if(user == null) {
      throw new UsernameNotFoundException("Username not found");
    }
    return user;
  }
}
