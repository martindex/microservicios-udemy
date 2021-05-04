package com.formacionbdi.springboot.app.productos.models.service;

import java.util.List;
import com.formacionbdi.springboot.app.productos.models.entity.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
}
