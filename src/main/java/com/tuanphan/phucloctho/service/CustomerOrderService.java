package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.CustomerOrder;
import com.tuanphan.phucloctho.model.CustomerOrderDetail;
import com.tuanphan.phucloctho.repository.CustomerOrderDetailRepository;
import com.tuanphan.phucloctho.repository.CustomerOrderRepository;
import com.tuanphan.phucloctho.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;
    private CustomerOrderDetailRepository orderDetailRepository;
    @Autowired
    public CustomerOrderService(CustomerOrderRepository repository,
                                CustomerOrderDetailRepository detailRepository){
        this.customerOrderRepository = repository;
        this.orderDetailRepository = detailRepository;
    }

    public List<CustomerOrder> findAll(){
        return customerOrderRepository.findAll();
    }

    @Transactional
    public CustomerOrder add(CustomerOrder customerOrder) {
        CustomerOrder addedOrder = customerOrderRepository.save(customerOrder);
        List<CustomerOrderDetail> orderDetailList = addedOrder.getCustomerOrderDetailList();
        orderDetailList.sort(Comparator.comparing(CustomerOrderDetail::getId));
        for (CustomerOrderDetail item : orderDetailList) {
            item.setCustomerOrderId(addedOrder.getId());
        }
        orderDetailRepository.saveAll(orderDetailList);
        return addedOrder;
    }
}
