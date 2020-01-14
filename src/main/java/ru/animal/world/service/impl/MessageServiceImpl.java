package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.mapper.MessageMapper;
import ru.animal.world.repository.MessageRepository;
import ru.animal.world.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private MessageMapper messageMapper;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, MessageMapper mapper) {
    this.messageRepository = messageRepository;
    this.messageMapper = mapper;
  }

  // TODO
  @Override
  public MessageDto create(MessageDto newMessageDto) {
    Message result = messageRepository.save(messageMapper.dtoToEntity(newMessageDto));
    return messageMapper.entityToDto(result);
  }

  @Override
  public MessageDto getById(Long id) {
    return messageMapper.entityToDto(messageRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Message.class.getSimpleName())));
  }

  @Override
  public Collection<MessageDto> getAll() {
    return messageRepository.findAll()
        .stream().map(messageMapper::entityToDto)
        .collect(Collectors.toList());
  }

  // TODO
  @Override
  public MessageDto update(MessageDto updateMessageDto, Long id) {
    Message message = messageRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Message.class.getSimpleName()));
    message = messageMapper.dtoToEntity(updateMessageDto);
    message.setId(id);
    return messageMapper.entityToDto(messageRepository.saveAndFlush(message));
  }

  @Override
  public boolean delete(Long id) {
    try {
      messageRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(Message.class.getSimpleName());
    }
  }
}