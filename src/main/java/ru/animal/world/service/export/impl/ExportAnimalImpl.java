package ru.animal.world.service.export.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.service.export.ExportAnimalService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;

@Service
public class ExportAnimalImpl implements ExportAnimalService {

    @Override
    public ByteArrayInputStream exportToPdf(Collection<AnimalDto> data) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // создаем таблицу в PDF
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Animal", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


            // заполняем PDF таблицу полями
            for (AnimalDto dto : data) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(dto.getAnimalId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(dto.getAnimalName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            ex.getStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream exportToDoc(Collection<AnimalDto> obj) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportToPdf(AnimalDto obj) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportToDoc(AnimalDto obj) {
        return null;
    }
}
