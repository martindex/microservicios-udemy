package com.formacionbdi.springboot.app.items.service;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Producto;

@Service
public class ItemServiceImpl implements ItemService{

    private final RestTemplate clientRestTemplate;

    @Autowired
    public ItemServiceImpl(RestTemplate clientRestTemplate) {
        this.clientRestTemplate = clientRestTemplate;
    }

    @Override
    public List<Item> findAll() {
        List<Producto> list = Arrays.asList(clientRestTemplate.getForObject("http://servicio-productos/producto/listar", Producto[].class));
        return list.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> mapPathVariables = new HashMap<String, String>();
        mapPathVariables.put("id", id.toString());
        Producto producto = clientRestTemplate.getForObject("http://servicio-productos/producto/ver/{id}", Producto.class, mapPathVariables);
        return new Item(producto, cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<>(producto);
        ResponseEntity<Producto> response = clientRestTemplate.exchange("http://servicio-productos/producto/crear", HttpMethod.POST, body, Producto.class);
        return response.getBody();
    }

    @Override
    public Producto update(Producto producto, Long id) {
        HttpEntity<Producto> body = new HttpEntity<>(producto);
        Map<String, String> mapPathVariables = new HashMap<String, String>();
        mapPathVariables.put("id", id.toString());
        ResponseEntity<Producto> response = clientRestTemplate.exchange(
                "http://servicio-productos/producto/editar/{id}", HttpMethod.PUT, body, Producto.class, mapPathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> mapPathVariables = new HashMap<String, String>();
        mapPathVariables.put("id", id.toString());
        clientRestTemplate.delete("http://servicio-productos/producto/eliminar/{id}", mapPathVariables);
    }
}
