package com.luandeptrai.toi.models;

import com.luandeptrai.toi.custom.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "CONVERSATION")
public class ConversationEntity extends BaseEntity {
  @Basic
  @Column(name = "CONTENT", length = 1024)
  private String content;
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_THREAD", nullable = false)
  private ThreadEntity thread;
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
}
