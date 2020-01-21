package ru.animal.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.service.AnimalService;
import ru.animal.world.service.export.ExportAnimalService;

import java.io.ByteArrayInputStream;
import java.util.Collection;

@RestController
@RequestMapping(value = "/animals/export")
public class ExportPdfResource {
    private final AnimalService animalService;
    private final ExportAnimalService exportService;

    @Autowired
    public ExportPdfResource(AnimalService animalService, ExportAnimalService exportService) {
        this.animalService = animalService;
        this.exportService = exportService;
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> animalToPdf() {
        Collection<AnimalDto> animalDtos = animalService.getAll();

        ByteArrayInputStream bis = exportService.exportToPdf(animalDtos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=animals.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
