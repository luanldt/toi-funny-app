package com.luandeptrai.toi.service.impl;

import com.luandeptrai.toi.dto.ContactDTO;
import com.luandeptrai.toi.dto.MetaDTO;
import com.luandeptrai.toi.dto.PagedDTO;
import com.luandeptrai.toi.dto.ThreadDTO;
import com.luandeptrai.toi.models.ContactEntity;
import com.luandeptrai.toi.models.ThreadEntity;
import com.luandeptrai.toi.models.UserEntity;
import com.luandeptrai.toi.repositories.ContactRepository;
import com.luandeptrai.toi.service.ContactService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

  private int PAGE_LENGTH = 15;

  private final ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  @Transactional
  public boolean create(List<String> listUser) {
    if(listUser == null || listUser.isEmpty()) {
      return false;
    }

    UserEntity user = new UserEntity(SecurityContextHolder.getContext().getAuthentication().getName());

    List<ContactEntity> listEntity = listUser.stream().map(t -> {
      ContactEntity entity = new ContactEntity();
      entity.setIdUser(user);
      entity.setAccepted(false);
      entity.setIdFriend(new UserEntity(t));
      entity.setDeleted(false);
      return entity;
    }).collect(Collectors.toList());

    this.contactRepository.save(listEntity);

    return true;

  }


  @Override
  public PagedDTO<ContactDTO> list(int page) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Pageable pageable = new PageRequest(page, PAGE_LENGTH);
    Page<ContactEntity> pagedEntity = this.contactRepository.findAllContact(username, true, false, pageable);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addMappings(new PropertyMap<ContactEntity, ContactDTO>() {
      @Override
      protected void configure() {
        map().setUsername(source.getIdFriend().getUsername());
      }
    });
    return new PagedDTO<>(new MetaDTO(page, PAGE_LENGTH, pagedEntity.getTotalPages(), pagedEntity.getTotalElements()), pagedEntity.getContent().stream().map(t ->
        modelMapper.map(t, ContactDTO.class)
    ).collect(Collectors.toList()));
  }
}
