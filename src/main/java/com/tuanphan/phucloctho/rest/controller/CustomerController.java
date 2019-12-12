package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Customer;
import com.tuanphan.phucloctho.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService service){
        this.customerService = service;
    }

    @GetMapping("")
    public Object getAllCustomers(){
        List<Customer> customerList = customerService.findAll();
        if(customerList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object addCustomer(@Valid @RequestBody Customer customer,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        Customer addedCustomer = customerService.add(customer);
        return new ResponseEntity<>(addedCustomer,HttpStatus.OK);
    }

    @PutMapping("")
    public Object updateCustomer(@Valid @RequestBody Customer customer,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        if(!customerService.existsById(customer.getId())){
            bindingResult.rejectValue("id","customer.id","Khách hàng không tồn tại.");
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Customer updatedCustomer = customerService.add(customer);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable int id){
        if(customerService.deleteById(id))
            return new ResponseEntity<>("Xóa khách hàng thành công.",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Xóa khách hàng thất bại.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/email/{name}")
    public Object findByName(@PathVariable String name){
        List<Customer> customerList = customerService.findByName(name);
        if(customerList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy khách hàng",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/taxcode/{taxCode}")
    public Object findByTaxCode(@PathVariable String taxCode){
        List<Customer> customerList = customerService.findByTaxCode(taxCode);
        if(customerList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy khách hàng",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }


}
