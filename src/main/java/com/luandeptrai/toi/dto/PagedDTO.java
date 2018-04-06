package com.luandeptrai.toi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagedDTO<T> {
  private MetaDTO meta;
  private List<T> elements;

  public PagedDTO(MetaDTO meta, List<T> elements) {
    this.meta = meta;
    this.elements = elements;
  }
}
