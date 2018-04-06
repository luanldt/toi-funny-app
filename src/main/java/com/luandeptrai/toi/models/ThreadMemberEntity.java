package com.luandeptrai.toi.models;

import com.luandeptrai.toi.custom.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "THREAD_MEMBER")
public class ThreadMemberEntity extends BaseEntity {
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_THREAD", nullable = false)
  private ThreadEntity thread;
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_USER",  referencedColumnName = "USERNAME", nullable = false)
  private UserEntity user;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Basic
  @CreatedDate
  @Column(name = "CREATED_DATE")
  private Date createdDate;
}
