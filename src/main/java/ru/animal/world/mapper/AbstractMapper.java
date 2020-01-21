package ru.animal.world.mapper;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import ru.animal.world.dto.BaseDto;
import ru.animal.world.entity.BaseEntity;

@Slf4j
public abstract class AbstractMapper<Entity extends BaseEntity, Dto extends BaseDto> implements Mapper<Entity, Dto> {

  protected ModelMapper modelMapper;
  private Class<Entity> entityClass;
  private Class<Dto> dtoClass;

  AbstractMapper(Class<Entity> entityClass, Class<Dto> dtoClass, ModelMapper mapper) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
    this.modelMapper = mapper;
  }

  @Override
  public Entity dtoToEntity(Dto dto) {
    return Objects.isNull(dto)
        ? null
        : modelMapper.map(dto, entityClass);
  }

  @Override
  public Dto entityToDto(Entity entity) {
    return Objects.isNull(entity)
        ? null
        : modelMapper.map(entity, dtoClass);
  }

  Converter<Entity, Dto> toDtoConverter() {
    return context -> {
      Entity source = context.getSource();
      Dto destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  Converter<Dto, Entity> toEntityConverter() {
    return context -> {
      Dto source = context.getSource();
      Entity destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  void mapSpecificFields(Entity source, Dto destination) {
  }

  void mapSpecificFields(Dto source, Entity destination) {
  }
}