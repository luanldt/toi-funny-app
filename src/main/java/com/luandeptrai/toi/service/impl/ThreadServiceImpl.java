package com.luandeptrai.toi.service.impl;

import com.luandeptrai.toi.dto.MetaDTO;
import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import com.luandeptrai.toi.models.ThreadEntity;
import com.luandeptrai.toi.models.ThreadMemberEntity;
import com.luandeptrai.toi.models.UserEntity;
import com.luandeptrai.toi.repositories.ThreadRepository;
import com.luandeptrai.toi.service.ThreadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;


@Service
public class ThreadServiceImpl implements ThreadService {

  private int PAGE_LENGTH = 15;

  @Autowired
  private final ThreadRepository threadRepository;

  public ThreadServiceImpl(ThreadRepository threadRepository) {
    this.threadRepository = threadRepository;
  }

  @Override
  @Transactional
  public ThreadDTO create(ThreadDTO dto) {
    ThreadEntity threadEntity = new ThreadEntity();
    threadEntity.setThreadName(dto.getThreadName());
    threadEntity.setDeleted(false);

    // get current id
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    dto.getMembers().add(username);
    threadEntity.setThreadMemberEntity(dto.getMembers().stream().map(t -> {
      ThreadMemberEntity threadMemberEntity = new ThreadMemberEntity();
      threadMemberEntity.setUser(new UserEntity(t));
      threadMemberEntity.setThread(threadEntity);
      threadMemberEntity.setDeleted(false);
      return threadMemberEntity;
    }).collect(Collectors.toList()));
    ThreadEntity entitySaved = this.threadRepository.save(threadEntity);
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(entitySaved, ThreadDTO.class);
  }


  @Override
  public PagedDTO<ThreadDTO> list(int page) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Pageable pageable = new PageRequest(page, PAGE_LENGTH);
    Page<ThreadEntity> pagedEntity = this.threadRepository.findThreadHistory(username, false, pageable);
    ModelMapper modelMapper = new ModelMapper();
    return new PagedDTO<>(new MetaDTO(page, PAGE_LENGTH, pagedEntity.getTotalPages(), pagedEntity.getTotalElements()), pagedEntity.getContent().stream().map(t ->
        modelMapper.map(t, ThreadDTO.class)
    ).collect(Collectors.toList()));
  }
}
