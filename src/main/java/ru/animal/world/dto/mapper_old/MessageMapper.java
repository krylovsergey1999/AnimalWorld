package ru.animal.world.dto.mapper_old;

import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;

public class MessageMapper implements Mapper<MessageDto, Message> {

  private DialogMapper dialogMapper = new DialogMapper();

  @Override
  public MessageDto entityToDto(Message message) {
    return MessageDto.builder()
        .id(message.getId())
        .text(message.getText())
        .messageTime(message.getMessageTime())
        .dialog(dialogMapper.entityToDto(message.getDialog()))
        .build();
  }

  @Override
  public Message dtoToEntity(MessageDto messageDto) {
    return Message.builder()
        .id(messageDto.getId())
        .text(messageDto.getText())
        .messageTime(messageDto.getMessageTime())
        .dialog(dialogMapper.dtoToEntity(messageDto.getDialog()))
        .build();
  }
}