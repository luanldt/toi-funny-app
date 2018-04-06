package com.luandeptrai.toi.models;

import com.luandeptrai.toi.custom.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "THREAD")
public class ThreadEntity extends BaseEntity {
  @Basic
  @Column(name = "THREAD_NAME", length = 128)
  private String threadName;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Basic
  @Column(name = "CREATED_USER", length = 25)
  @CreatedBy
  private String createdUser;
  @Basic
  @CreatedDate
  @Column(name = "CREATED_DATE")
  private Date createdDate;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "thread")
  private Collection<ConversationEntity> conversationEntities;
  @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Collection<ThreadMemberEntity> threadMemberEntity;
}
