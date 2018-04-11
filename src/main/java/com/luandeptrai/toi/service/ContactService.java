package com.luandeptrai.toi.service;

import com.luandeptrai.toi.dto.ContactDTO;
import com.luandeptrai.toi.dto.PagedDTO;

import java.util.List;

public interface ContactService {

  /**
   * Create user (add friend may be with many)
   * @param listUser list user
   * @return
   */
  boolean create(List<String> listUser);

  PagedDTO<ContactDTO> list(int page);

}
