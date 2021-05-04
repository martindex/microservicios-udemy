package com.formacionbdi.springboot.app.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;
import com.formacionbdi.springboot.app.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @Value("${custom.text}")
    private String texto;

    @Autowired
    public ItemController(@Qualifier("serviceFeign") ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    @HystrixCommand(fallbackMethod = "detalleIfFail")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id,cantidad);
    }

    public Item detalleIfFail(Long id, Integer cantidad){
        return new Item(new Producto(),cantidad);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${custom.text}") String other){
        Map<String, String> map = new HashMap();
        map.put("texto", texto);
        map.put("otro", other);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}