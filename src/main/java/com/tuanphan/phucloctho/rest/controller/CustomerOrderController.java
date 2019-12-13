package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.CustomerOrder;
import com.tuanphan.phucloctho.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer-orders")
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService service){
        this.customerOrderService = service;
    }
    @GetMapping("")
    public Object findAll(){
        List<CustomerOrder> customerOrderList = customerOrderService.findAll();
        if(customerOrderList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerOrderList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody CustomerOrder customerOrder,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        CustomerOrder addedCustomerOrder = customerOrderService.add(customerOrder);
        if(addedCustomerOrder == null)
            return new ResponseEntity<>("Thêm đơn hàng thất bại.\nVui lòng kiểm tra lại số lượng hàng tồn kho",
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(addedCustomerOrder,HttpStatus.OK);
    }
}
