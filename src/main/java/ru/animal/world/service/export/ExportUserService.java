package ru.animal.world.service.export;

import ru.animal.world.dto.UserDto;

import java.io.ByteArrayInputStream;

public interface ExportUserService extends
        ExportCollectionService<ByteArrayInputStream, UserDto>,
        ExportSingleEntityService<ByteArrayInputStream, UserDto> {
}
