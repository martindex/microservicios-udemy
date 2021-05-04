package com.formacionbdi.springboot.app.items.service;

import java.util.List;
import com.formacionbdi.springboot.app.items.models.Item;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
}
