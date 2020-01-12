package ru.animal.world.service.export;

public interface ExportSingleEntityService<T, E> {
    T exportToPdf(E obj);

    T exportToDoc(E obj);
}
