package com.luandeptrai.toi.repositories;

import com.luandeptrai.toi.models.ConversationEntity;
import com.luandeptrai.toi.models.ThreadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, String> {

  @Query("select m from ConversationEntity m where m.thread.idCode = ?1 and m.isDeleted = ?2 order by m.createdDate desc")
  Page<ConversationEntity> findConversationByThread(String idThread, boolean isDeleted, Pageable pageable);

}
