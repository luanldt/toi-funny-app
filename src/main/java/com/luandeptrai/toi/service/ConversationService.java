package com.luandeptrai.toi.service;

import com.luandeptrai.toi.dto.ConversationDTO;
import com.luandeptrai.toi.dto.PagedDTO;

public interface ConversationService {
  ConversationDTO create(ConversationDTO dto);

  PagedDTO<ConversationDTO> list(String idThread, int page);

}
