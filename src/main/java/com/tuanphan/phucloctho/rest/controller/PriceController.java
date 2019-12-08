package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Price;
import com.tuanphan.phucloctho.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private PriceService priceService;

    @Autowired
    public PriceController(PriceService service){
        this.priceService = service;
    }

    @GetMapping("")
    public Object findAll(){
        List<Price> priceList = priceService.findAll();
        if(priceList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(priceList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody Price price,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        Price addedPrice = priceService.add(price);
        if(addedPrice == null)
            return new ResponseEntity<>("Thêm giá thất bại.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(addedPrice,HttpStatus.OK);
    }
}
