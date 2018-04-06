package com.luandeptrai.toi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConversationDTO {
  private String idCode;
  private String content;
  private String idThread;
  private Date createdDate;
  private boolean me;
  private String username;
}
