package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {

  @Autowired
  public AnimalMapper(ModelMapper mapper) {
    super(Animal.class, AnimalDto.class, mapper);
  }

  @PostConstruct
  public void setupMapper() {
    modelMapper.createTypeMap(Animal.class, AnimalDto.class)
        .addMappings(m -> m.skip(AnimalDto::setUserId)).setPostConverter(toDtoConverter());
    modelMapper.createTypeMap(AnimalDto.class, Animal.class)
        .addMappings(m -> m.skip(Animal::setUsersAnimal)).setPostConverter(toEntityConverter());
  }

  @Override
  void mapSpecificFields(Animal source, AnimalDto destination) {
    destination.setUserId(getId(source));
  }

  private Long getId(Animal source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUsersAnimal().getId();
  }
}