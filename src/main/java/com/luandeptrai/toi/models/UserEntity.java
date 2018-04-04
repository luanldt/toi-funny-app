package com.luandeptrai.toi.models;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails {
  @Id
  @Column(name = "ID_CODE", length= 25)
  private String idCode;
  @Basic
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
  private Date createdDate;
  @OneToMany(mappedBy = "user")
  private Collection<ThreadMemberEntity> threadMemberEntity;
  @PrePersist
  private void generateIdCode() {
    if(this.idCode == null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyyHHmmss");
      String dateTime = simpleDateFormat.format(new Date());
      String uuid = UUID.randomUUID().toString();
      this.idCode = String.join("", dateTime, uuid.replaceAll("-", "")).substring(25).toLowerCase();
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
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
}
