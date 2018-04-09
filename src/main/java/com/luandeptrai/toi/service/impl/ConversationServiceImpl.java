package com.luandeptrai.toi.service.impl;

import com.luandeptrai.toi.dto.ConversationDTO;
import com.luandeptrai.toi.dto.MetaDTO;
import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import com.luandeptrai.toi.models.ConversationEntity;
import com.luandeptrai.toi.models.ThreadEntity;
import com.luandeptrai.toi.repositories.ConversationRepository;
import com.luandeptrai.toi.service.ConversationService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class ConversationServiceImpl implements ConversationService {

  private final int PAGE_LENGTH = 15;

  @Autowired
  private ConversationRepository conversationRepository;

  @Override
  @Transactional
  public ConversationDTO create(ConversationDTO dto) {
    ModelMapper modelMapper = new ModelMapper();
    Converter<String, ThreadEntity> convertToThreadEntity = mappingContext -> new ThreadEntity(mappingContext.getSource());
    modelMapper.addMappings(new PropertyMap<ConversationDTO, ConversationEntity>() {
      @Override
      protected void configure() {
        using(convertToThreadEntity).map(source.getIdThread(), destination.getThread());
      }
    });
    ConversationEntity entity = modelMapper.map(dto, ConversationEntity.class);
    ConversationEntity entitySaved = this.conversationRepository.save(entity);
    dto.setCreatedDate(entitySaved.getCreatedDate());
    dto.setMe(true);
    dto.setIdCode(entitySaved.getIdCode());
    return dto;
  }

  @Override
  public PagedDTO<ConversationDTO> list(String idThread, int page) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Pageable pageable = new PageRequest(page, PAGE_LENGTH);
    Page<ConversationEntity> pagedEntity = this.conversationRepository.findConversationByThread(idThread, false, pageable);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addMappings(new PropertyMap<ConversationEntity, ConversationDTO>() {
      @Override
      protected void configure() {
        map().setIdThread(source.getThread().getIdCode());
      }
    });
    return new PagedDTO<>(new MetaDTO(page, PAGE_LENGTH, pagedEntity.getTotalPages(),
        pagedEntity.getTotalElements()), pagedEntity.getContent().stream().map(t -> {
      ConversationDTO dto = modelMapper.map(t, ConversationDTO.class);
      dto.setMe(username.equals(t.getCreatedUser()));
      return dto;
    }).sorted(Comparator.comparing(ConversationDTO::getCreatedDate)).collect(Collectors.toList()));
  }
}
