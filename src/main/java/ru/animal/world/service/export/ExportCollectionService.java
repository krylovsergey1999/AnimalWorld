package ru.animal.world.service.export;

import java.util.Collection;

public interface ExportCollectionService<T, E> {
    T exportToPdf(Collection<E> obj);

    T exportToDoc(Collection<E> obj);
}
