package ru.animal.world.dto.mapper_old;

import java.util.stream.Collectors;
import ru.animal.world.dto.DialogDto;
import ru.animal.world.entity.Dialog;

public class DialogMapper implements Mapper<DialogDto, Dialog> {

  private MessageMapper messageMapper = new MessageMapper();
  private UserMapper userMapper = new UserMapper();

  @Override
  public DialogDto entityToDto(Dialog dialog) {
    return DialogDto.builder()
        .id(dialog.getId())
        .textBody(dialog.getTextBody())
        .messages(dialog.getMessages()
            .stream().map(message -> messageMapper.entityToDto(message))
            .collect(Collectors.toSet()))
        .users_dialog(dialog.getUsers_dialog()
            .stream().map(user -> userMapper.entityToDto(user))
            .collect(Collectors.toSet()))
        .build();
  }

  @Override
  public Dialog dtoToEntity(DialogDto dialogDto) {
    return Dialog.builder()
        .id(dialogDto.getId())
        .textBody(dialogDto.getTextBody())
        .messages(dialogDto.getMessages()
            .stream().map(messageDto -> messageMapper.dtoToEntity(messageDto))
            .collect(Collectors.toSet()))
        .users_dialog(dialogDto.getUsers_dialog()
            .stream().map(userDto -> userMapper.dtoToEntity(userDto))
            .collect(Collectors.toSet()))
        .build();
  }
}