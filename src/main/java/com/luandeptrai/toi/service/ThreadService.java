package com.luandeptrai.toi.service;

import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;

public interface ThreadService {
  ThreadDTO create(ThreadDTO threadDTO);

  PagedDTO<ThreadDTO> list(int page);
}
