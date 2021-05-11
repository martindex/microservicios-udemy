package ar.martindex.ms.productos.models.repository;

import org.springframework.data.repository.CrudRepository;
import ar.martindex.ms.commons.models.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
