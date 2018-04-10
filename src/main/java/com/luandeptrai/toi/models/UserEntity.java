package com.luandeptrai.toi.models;

import com.luandeptrai.toi.custom.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails {
  @Basic
  @Column(name = "ID_CODE", length = 25)
  protected String idCode;
  @Id
  @Column(name = "USERNAME", length = 128)
  @NotNull
  private String username;
  @Basic
  @Column(name = "PASSWORD", length = 60)
  @NotNull
  private String password;
  @Basic
  @Column(name = "IS_ENABLE")
  private boolean isEnable;
  @Basic
  @Column(name = "IS_DELETED")
  private boolean isDeleted;
  @Basic
  @Column(name = "CREATED_DATE")
  @CreatedDate
  private Date createdDate;
  @OneToMany(mappedBy = "user")
  private Collection<ThreadMemberEntity> threadMemberEntity;

  public UserEntity(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = Collections.EMPTY_LIST;
    return grantedAuthorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnable;
  }

  @PrePersist
  private void generateIdCode() {
    if(this.idCode == null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyyHHmmss");
      String dateTime = simpleDateFormat.format(new Date());
      String uuid = UUID.randomUUID().toString();
      this.idCode = String.join("", dateTime, uuid.replace("-", "")).substring(25).toLowerCase();
    }
  }

  @OneToMany(mappedBy = "idFriend")
  private Collection<ContactEntity> contactEntityFriend;

  public Collection<ContactEntity> getContactEntity() {
    return contactEntityFriend;
  }

  public void setContactEntity(Collection<ContactEntity> contactEntityFriend) {
    this.contactEntityFriend = contactEntityFriend;
  }

  @OneToMany(mappedBy = "idUser")
  private Collection<ContactEntity> contactEntityUser;

  public Collection<ContactEntity> getContactEntityUser() {
    return contactEntityUser;
  }

  public void setContactEntityUser(Collection<ContactEntity> contactEntityUser) {
    this.contactEntityUser = contactEntityUser;
  }
}
