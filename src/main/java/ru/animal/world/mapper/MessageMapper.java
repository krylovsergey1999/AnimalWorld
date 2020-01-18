package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;
import ru.animal.world.repository.DialogRepository;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {

  private ModelMapper modelMapper;
  private DialogRepository dialogRepository;

  @Autowired
  public MessageMapper(ModelMapper modelMapper, DialogRepository dialogRepository) {
    super(Message.class, MessageDto.class, modelMapper);
    this.dialogRepository = dialogRepository;
    this.modelMapper = modelMapper;
  }

  @PostConstruct
  public void setupMapper() {
    modelMapper.createTypeMap(Message.class, MessageDto.class)
        .addMappings(m -> m.skip(MessageDto::setDialogId)).setPostConverter(toDtoConverter());
    modelMapper.createTypeMap(MessageDto.class, Message.class)
        .addMappings(m -> m.skip(Message::setDialog)).setPostConverter(toEntityConverter());
  }

  @Override
  void mapSpecificFields(Message source, MessageDto destination) {
    destination.setDialogId(getId(source));
  }

  private Long getId(Message source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDialog().getId();
  }

  @Override
  void mapSpecificFields(MessageDto source, Message destination) {
    destination.setDialog(dialogRepository.findById(source.getDialogId()).orElse(null));
  }
}