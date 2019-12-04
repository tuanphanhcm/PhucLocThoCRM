package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Customer;
import com.tuanphan.phucloctho.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public Object getAllCustomers(){
        List<Customer> customerList = customerService.findAll();
        if(customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }


}
