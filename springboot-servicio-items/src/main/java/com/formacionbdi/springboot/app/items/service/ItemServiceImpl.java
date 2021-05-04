package com.formacionbdi.springboot.app.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Producto> list = Arrays.asList(clientRestTemplate.getForObject("http://localhost:8001/producto/listar", Producto[].class));
        return list.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> mapPathVariables = new HashMap<String, String>();
        mapPathVariables.put("id", id.toString());
        Producto producto = clientRestTemplate.getForObject("http://localhost:8001/producto/ver/{id}", Producto.class, mapPathVariables);
        return new Item(producto, cantidad);
    }
}
