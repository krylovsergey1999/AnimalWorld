package ru.animal.world.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.DialogDto;
import ru.animal.world.entity.Dialog;

@Component
public class DialogMapper extends AbstractMapper<Dialog, DialogDto> {

  @Autowired
  public DialogMapper(ModelMapper mapper) {
    super(Dialog.class, DialogDto.class, mapper);
  }
}