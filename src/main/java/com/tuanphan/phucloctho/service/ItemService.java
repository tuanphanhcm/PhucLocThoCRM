package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Item;
import com.tuanphan.phucloctho.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository repository){
        this.itemRepository = repository;
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item add(Item item){
        return itemRepository.save(item);
    }


}
