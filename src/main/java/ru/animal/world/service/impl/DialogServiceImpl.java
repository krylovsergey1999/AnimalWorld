package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.DialogDto;
import ru.animal.world.entity.Dialog;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.DialogRepository;
import ru.animal.world.service.DialogService;

@Service
public class DialogServiceImpl implements DialogService {

  private final DialogRepository dialogRepository;
  //  private Mapper<DialogDto, Dialog> dialogMapper = new DialogMapper();
  private ru.animal.world.mapper.DialogMapper dialogMapper = new ru.animal.world.mapper.DialogMapper();

  @Autowired
  public DialogServiceImpl(DialogRepository DialogRepository) {
    this.dialogRepository = DialogRepository;
  }

  @Override
  public DialogDto create(DialogDto newDialogDto) {
    Dialog result = dialogRepository.save(dialogMapper.dtoToEntity(newDialogDto));
    return dialogMapper.entityToDto(result);
  }

  @Override
  public DialogDto getById(Long id) {
    return dialogMapper.entityToDto(dialogRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Dialog.class.getSimpleName())));
  }

  @Override
  public Collection<DialogDto> getAll() {
    return dialogRepository.findAll()
        .stream().map(dialogMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public DialogDto update(DialogDto updateDialogDto, Long id) {
    Dialog dialog = dialogRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Dialog.class.getSimpleName()));
    dialog = dialogMapper.dtoToEntity(updateDialogDto);
    dialog.setId(id);
    return dialogMapper.entityToDto(dialogRepository.saveAndFlush(dialog));
  }

  @Override
  public boolean delete(Long id) {
    try {
      dialogRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(Dialog.class.getSimpleName());
    }
  }
}