package ru.animal.world.service.export;

import java.io.ByteArrayInputStream;
import java.util.Collection;

public interface ExportPdfService<D> {
    ByteArrayInputStream generatePdf(Collection<D> data);
}
