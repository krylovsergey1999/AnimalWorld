package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.CommentDto;
import ru.animal.world.dto.mapper.CommentMapper;
import ru.animal.world.dto.mapper.Mapper;
import ru.animal.world.entity.Comment;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.CommentRepository;
import ru.animal.world.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private Mapper<CommentDto, Comment> commentMapper = new CommentMapper();

  @Autowired
  public CommentServiceImpl(CommentRepository CommentRepository) {
    this.commentRepository = CommentRepository;
  }

  @Override
  public CommentDto create(CommentDto newCommentDto) {
    Comment result = commentRepository.save(commentMapper.dtoToEntity(newCommentDto));
    return commentMapper.entityToDto(result);
  }

  @Override
  public CommentDto getById(Long id) {
    return commentMapper.entityToDto(commentRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Comment.class.getSimpleName())));
  }

  @Override
  public Collection<CommentDto> getAll() {
    return commentRepository.findAll()
        .stream().map(commentMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public CommentDto update(CommentDto updateCommentDto, Long id) {
    return commentRepository.findById(id).map(CommentInDB -> {
      // Todo после обновления всех сущностей
      return commentMapper.entityToDto(commentRepository.saveAndFlush(CommentInDB));
    }).orElseThrow(() -> new NotFoundException(Comment.class.getSimpleName()));
  }

  @Override
  public boolean delete(Long id) {
    try {
      commentRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(Comment.class.getSimpleName());
    }
  }
}