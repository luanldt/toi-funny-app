package com.luandeptrai.toi.controller;

import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import com.luandeptrai.toi.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/threads")
public class ThreadResource {

  private final ThreadService threadService;

  @Autowired
  public ThreadResource(ThreadService threadService) {
    this.threadService = threadService;
  }

  @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
  public ResponseEntity<ThreadDTO> create(@RequestBody ThreadDTO dto) {
    ThreadDTO result = this.threadService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
  public ResponseEntity<PagedDTO> list(@RequestParam(value = "page", name = "page", defaultValue = "0", required = false) int page) {
    PagedDTO result = this.threadService.list(page);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
