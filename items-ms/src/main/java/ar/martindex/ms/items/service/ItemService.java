package ar.martindex.ms.items.service;

import java.util.List;
import ar.martindex.ms.commons.models.entities.Producto;
import ar.martindex.ms.items.models.Item;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
    Producto save(Producto producto);
    Producto update(Producto producto, Long id);
    void delete(Long id);
}
