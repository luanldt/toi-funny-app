package com.luandeptrai.toi.repositories;

import com.luandeptrai.toi.models.ThreadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<ThreadEntity, String> {

  @Query("select m from ThreadEntity m inner join m.threadMemberEntity a left join m.conversationEntities b where m.isDeleted = ?2 and a.user.username = ?1 and a.isDeleted = ?2 order by b.createdDate desc ")
  Page<ThreadEntity> findThreadHistory(String username, boolean isDeleted, Pageable pageable);

}
