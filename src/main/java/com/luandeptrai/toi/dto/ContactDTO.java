package com.luandeptrai.toi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {
  private List<String> listUser;

  private String username;

}
