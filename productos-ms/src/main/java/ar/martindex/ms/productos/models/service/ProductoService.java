package ar.martindex.ms.productos.models.service;

import java.util.List;
import ar.martindex.ms.commons.models.entities.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
}
