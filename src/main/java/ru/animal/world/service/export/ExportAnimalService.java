package ru.animal.world.service.export;

import ru.animal.world.dto.AnimalDto;

import java.io.ByteArrayInputStream;

public interface ExportAnimalService extends
        ExportCollectionService<ByteArrayInputStream, AnimalDto>,
        ExportSingleEntityService<ByteArrayInputStream, AnimalDto> {
}
