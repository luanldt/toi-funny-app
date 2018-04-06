package com.luandeptrai.toi.custom;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {
  @Id
  @Column(name = "ID_CODE", length = 25)
  protected String idCode;
  @PrePersist
  private void generateIdCode() {
    if(this.idCode == null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyyHHmmss");
      String dateTime = simpleDateFormat.format(new Date());
      String uuid = UUID.randomUUID().toString();
      this.idCode = String.join("", dateTime, uuid.replace("-", "")).substring(25).toLowerCase();
    }
  }

}
