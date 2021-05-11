package ar.martindex.ms.items.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ar.martindex.ms.commons.models.entities.Producto;
import ar.martindex.ms.items.clients.ProductoClientRest;
import ar.martindex.ms.items.models.Item;

@Service
@Primary
public class ItemServiceFeign implements ItemService{

    private final ProductoClientRest productoClientRest;

    @Autowired
    public ItemServiceFeign(ProductoClientRest productoClientRest) {
        this.productoClientRest = productoClientRest;
    }

    @Override
    public List<Item> findAll() {
        return productoClientRest.listar().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(productoClientRest.detalle(id), cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return productoClientRest.crearProducto(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return productoClientRest.editarProducto(producto, id);
    }

    @Override
    public void delete(Long id) {
        productoClientRest.eliminarProducto(id);
    }
}
