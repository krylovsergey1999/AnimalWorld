package ru.animal.world.service.export.impl;

import ru.animal.world.dto.UserDto;
import ru.animal.world.service.export.ExportUserService;

import java.io.ByteArrayInputStream;
import java.util.Collection;

public class ExportUser implements ExportUserService {
    @Override
    public ByteArrayInputStream exportToPdf(Collection<UserDto> obj) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportToDoc(Collection<UserDto> obj) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportToPdf(UserDto obj) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportToDoc(UserDto obj) {
        return null;
    }
}
