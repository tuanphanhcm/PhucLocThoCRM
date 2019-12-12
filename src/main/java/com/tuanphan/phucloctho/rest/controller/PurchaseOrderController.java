package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.PurchaseOrder;
import com.tuanphan.phucloctho.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @GetMapping("")
    public Object findAll(){
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.findAll();
        if(purchaseOrderList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(purchaseOrderList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody PurchaseOrder purchaseOrder,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        PurchaseOrder addedOrder = purchaseOrderService.add(purchaseOrder);
        return new ResponseEntity<>(addedOrder, HttpStatus.OK);
    }
}
