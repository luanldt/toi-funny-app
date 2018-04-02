package com.luandeptrai.toi.models;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "THREAD")
@EntityListeners(AuditingEntityListener.class)
public class ThreadEntity {
  @Id
  @Column(name = "ID_CODE", length = 25)
  private String idCode;
  @Basic
  @Column(name = "THREAD_NAME", length = 128)
  private String threadName;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Basic
  @Column(name = "CREATED_USER", length = 25)
  private String createdUser;
  @Basic
  @Column(name = "CREATED_DATE")
  private String createdDate;
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
