package com.luandeptrai.toi.repositories;

import com.luandeptrai.toi.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  UserEntity findByUsernameAndIsDeleted(String username, boolean isDeleted);
}
