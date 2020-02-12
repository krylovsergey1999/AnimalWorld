package ru.animal.world.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.animal.world.dto.UserDto;

public interface UserService extends AbstractService<UserDto>, UserDetailsService {

}