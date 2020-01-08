package ru.animal.world.dto.mapper;

import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;

// Todo после обновления всех сущностей
public class MessageMapper implements Mapper<MessageDto, Message> {

  @Override
  public MessageDto entityToDto(Message message) {
    return null;
  }

  @Override
  public Message dtoToEntity(MessageDto messageDto) {
    return null;
  }
}