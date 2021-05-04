package com.formacionbdi.springboot.app.items.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.formacionbdi.springboot.app.items.models.Producto;

@FeignClient(name = "servicio-productos")
@RequestMapping("/producto")
public interface ProductoClientRest {
    @GetMapping("/listar")
    List<Producto> listar();
    @GetMapping("/ver/{id}")
    Producto detalle(@PathVariable Long id);
}
