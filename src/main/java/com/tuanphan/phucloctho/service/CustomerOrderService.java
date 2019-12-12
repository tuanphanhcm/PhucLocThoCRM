package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.CustomerOrder;
import com.tuanphan.phucloctho.model.CustomerOrderDetail;
import com.tuanphan.phucloctho.model.Price;
import com.tuanphan.phucloctho.repository.CustomerOrderDetailRepository;
import com.tuanphan.phucloctho.repository.CustomerOrderRepository;
import com.tuanphan.phucloctho.repository.CustomerRepository;
import com.tuanphan.phucloctho.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;
    private CustomerOrderDetailRepository orderDetailRepository;
    private PriceRepository priceRepository;
    @Autowired
    public CustomerOrderService(CustomerOrderRepository repository,
                                CustomerOrderDetailRepository detailRepository,
                                PriceRepository priceRepo){
        this.customerOrderRepository = repository;
        this.orderDetailRepository = detailRepository;
        this.priceRepository = priceRepo;
    }

    public List<CustomerOrder> findAll(){
        return customerOrderRepository.findAll();
    }

    @Transactional
    public CustomerOrder add(CustomerOrder customerOrder) {
        CustomerOrder addedOrder = customerOrderRepository.save(customerOrder);
        List<CustomerOrderDetail> orderDetailList = addedOrder.getCustomerOrderDetailList();
        orderDetailList.sort(Comparator.comparing(CustomerOrderDetail::getId));//to prevent deadlock
        for (CustomerOrderDetail item : orderDetailList) {
            item.setCustomerOrderId(addedOrder.getId());
            Optional<Price> price = priceRepository.findById(item.getPriceId());
            if(price.isPresent()) {
                float total = calculateTotal(price.get(),item);
                item.setTotal(total);
            }
        }
        orderDetailRepository.saveAll(orderDetailList);
        return addedOrder;
    }

    private float calculateTotal(Price price, CustomerOrderDetail orderDetail){
        float total;
        if(orderDetail.getDiscount() == 0)
            total = (float)price.getItemPrice() * orderDetail.getQuantity();
        else
            total = price.getItemPrice() * orderDetail.getQuantity() * (1 - orderDetail.getDiscount()/100);
        return total;
    }
}
