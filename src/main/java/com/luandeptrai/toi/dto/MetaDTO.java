package com.luandeptrai.toi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaDTO {
  private int currentPage;
  private int length;
  private int maxPage;
  private long limit;
}
