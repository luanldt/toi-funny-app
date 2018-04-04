package com.luandeptrai.toi.service;

import com.luandeptrai.toi.models.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

public interface UserServices extends UserDetailsService {

//  private PasswordEncode

  UserEntity save(UserEntity entity);
}
