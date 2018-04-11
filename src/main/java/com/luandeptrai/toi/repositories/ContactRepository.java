package com.luandeptrai.toi.repositories;

import com.luandeptrai.toi.models.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {

  @Query("select m from ContactEntity m where m.idUser.username = ?1 and m.isAccepted = ?2 and m.isDeleted = ?3")
  Page<ContactEntity> findAllContact(String username, boolean isAccepted, boolean isDeleted, Pageable pageable);

}
