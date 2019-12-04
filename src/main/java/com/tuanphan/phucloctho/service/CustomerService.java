package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Customer;
import com.tuanphan.phucloctho.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer add(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(int id){
        return customerRepository.findById(id).get();
    }

    public List<Customer> findByTaxCode(String taxCode){
        return customerRepository.findByTaxCode(taxCode);
    }
}
