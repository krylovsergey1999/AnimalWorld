package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.MessageDto;
import ru.animal.world.entity.Message;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {

  @Autowired
  public MessageMapper(ModelMapper mapper) {
    super(Message.class, MessageDto.class, mapper);
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
}