package ru.animal.world.mapper;

import java.util.Objects;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.animal.world.dto.BaseDto;
import ru.animal.world.entity.BaseEntity;

public abstract class AbstractMapper<Entity extends BaseEntity, Dto extends BaseDto> implements Mapper<Entity, Dto> {

  @Autowired
  ModelMapper mapper;     // TODO null pointer WTF???

  private Class<Entity> entityClass;
  private Class<Dto> dtoClass;

  AbstractMapper(Class<Entity> entityClass, Class<Dto> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
  }

  @Override
  public Entity dtoToEntity(Dto dto) {
    return Objects.isNull(dto)
        ? null
        : mapper.map(dto, entityClass);
  }

  @Override
  public Dto entityToDto(Entity entity) {
    return Objects.isNull(entity)
        ? null
        : mapper.map(entity, dtoClass);
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

  void mapSpecificFields(Dto destination, Entity source) {
  }
}