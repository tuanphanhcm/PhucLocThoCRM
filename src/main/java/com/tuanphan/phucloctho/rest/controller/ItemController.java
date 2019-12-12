package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.dto.ItemDto;
import com.tuanphan.phucloctho.model.Item;
import com.tuanphan.phucloctho.service.BrandService;
import com.tuanphan.phucloctho.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private ItemService itemService;
    private BrandService brandService;
    @Autowired
    public ItemController(ItemService iservice,
                          BrandService bservice){
        this.itemService = iservice;
        this.brandService = bservice;
    }

    @GetMapping("")
    public Object findAll(){
        List<ItemDto> itemList = itemService.findAllItemDto();
        if(itemList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(itemList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody Item item,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        if(!brandService.existsById(item.getBrandId())){
            bindingResult.rejectValue("brandId","item.brandId","Hãng sản xuất không tồn tại.");
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        item.setId(0);
        Item addedItem = itemService.add(item);
        return new ResponseEntity<>(addedItem,HttpStatus.OK);
    }
}
