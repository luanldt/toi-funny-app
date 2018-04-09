package com.luandeptrai.toi.controller;

import com.luandeptrai.toi.dto.ConversationDTO;
import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import com.luandeptrai.toi.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ConversationResource {

  @Autowired
  private ConversationService conversationService;

  @RequestMapping(value = {"/threads/{idCode}/conversations", "/threads/{idCode}/conversations/"}, method = RequestMethod.GET)
  public ResponseEntity<PagedDTO<ConversationDTO>> list(@PathVariable("idCode") String idThread,
      @RequestParam(name = "page", required = false, defaultValue = "0", value = "page") int page) {
    PagedDTO<ConversationDTO> result = this.conversationService.list(idThread, page);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @RequestMapping(value = {"/threads/{idCode}/conversations", "/threads/{idCode}/conversations/"},
      method = RequestMethod.POST)
  public ResponseEntity<ConversationDTO> create(@PathVariable("idCode") String idThread,
                               @RequestBody ConversationDTO dto) {
    dto.setIdThread(idThread);
    ConversationDTO dtoSaved = this.conversationService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(dtoSaved);
  }

}
