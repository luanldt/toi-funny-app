package com.luandeptrai.toi.controller;

import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ConversationResource {

  @RequestMapping(value = {"/threads/{idCode}/conversations", "/threads/{idCode}/conversations/"}, method = RequestMethod.GET)
  public PagedDTO<ThreadDTO> list(@PathVariable("idCode") String idThread,
      @RequestParam(name = "page", required = false, defaultValue = "0", value = "page") int page) {
    return null;
  }

}
