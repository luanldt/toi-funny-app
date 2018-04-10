package com.luandeptrai.toi.models;

import com.luandeptrai.toi.custom.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CONTACT")
public class ContactEntity  extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "ID_USER", referencedColumnName = "USERNAME")
  private UserEntity idUser;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "ID_FRIEND", referencedColumnName = "USERNAME")
  private UserEntity idFriend;
  @Basic
  @Column(name = "IS_ACCEPTED")
  private String isAccepted;
  @Basic
  @CreatedDate
  @Column(name = "CREATED_DATE")
  private String createdDate;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
}
