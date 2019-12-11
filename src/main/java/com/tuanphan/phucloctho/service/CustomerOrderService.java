package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.CustomerOrder;
import com.tuanphan.phucloctho.repository.CustomerOrderRepository;
import com.tuanphan.phucloctho.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderService(CustomerOrderRepository repository){
        this.customerOrderRepository = repository;
    }

    public List<CustomerOrder> findAll(){
        return customerOrderRepository.findAll();
    }


}
