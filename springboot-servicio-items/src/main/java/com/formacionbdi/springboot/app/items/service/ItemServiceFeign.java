package com.formacionbdi.springboot.app.items.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formacionbdi.springboot.app.items.clients.ProductoClientRest;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;

@Service("serviceFeign")
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
        return null;
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
