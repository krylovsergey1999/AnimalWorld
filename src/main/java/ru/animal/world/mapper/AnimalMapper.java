package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;
import ru.animal.world.repository.UserRepository;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {

  private UserRepository userRepository;

  @Autowired
  public AnimalMapper(ModelMapper mapper, UserRepository userRepository) {
    super(Animal.class, AnimalDto.class, mapper);
    this.userRepository = userRepository;
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

  @Override
  void mapSpecificFields(AnimalDto source, Animal destination) {
    destination.setUsersAnimal(userRepository.findById(source.getUserId()).orElse(null));
  }
}