package com.luandeptrai.toi.models;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "THREAD_MEMBER")
@EntityListeners(AuditingEntityListener.class)
public class ThreadMemberEntity {
  @Id
  @Column(name = "ID_CODE", length= 25)
  private String idCode;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_THREAD", nullable = false)
  private ThreadEntity thread;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_USER", nullable = false)
  private UserEntity user;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Basic
  @Column(name = "CREATED_DATE")
  private Date createdDate;
  @PrePersist
  private void generateIdCode() {
    if(this.idCode == null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyyHHmmss");
      String dateTime = simpleDateFormat.format(new Date());
      String uuid = UUID.randomUUID().toString();
      this.idCode = String.join("", dateTime, uuid.replace("-", "").substring(10)).toLowerCase();
    }
  }
}
