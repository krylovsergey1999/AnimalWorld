package ru.animal.world.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {

  @Autowired
  public MessageMapper() {
    super(Message.class, MessageDto.class);
  }
}