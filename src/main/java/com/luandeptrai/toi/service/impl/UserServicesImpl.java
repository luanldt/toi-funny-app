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

@Service
public class UserServicesImpl implements UserServices {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserEntity save(UserEntity entity) {
    entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
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
