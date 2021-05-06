package com.formacionbdi.springboot.app.items.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

@FeignClient(name = "servicio-productos")
@RequestMapping("/producto")
public interface ProductoClientRest {
    @GetMapping("/listar")
    List<Producto> listar();
    @GetMapping("/ver/{id}")
    Producto detalle(@PathVariable Long id);
    @PostMapping("/crear")
    Producto crearProducto(@RequestBody Producto producto);
    @PutMapping("/editar/{id}")
    Producto editarProducto(@RequestBody Producto producto, @PathVariable Long id);
    @DeleteMapping("/eliminar/{id}")
    void eliminarProducto(@PathVariable Long id);
}
