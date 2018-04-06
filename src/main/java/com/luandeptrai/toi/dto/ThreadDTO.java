package com.luandeptrai.toi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ThreadDTO {
  private String idCode;
  private String threadName;
  private boolean isGroup;
  private int numberMember;
  private List<String> members;
}
