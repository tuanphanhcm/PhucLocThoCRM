package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.CustomerOrder;
import com.tuanphan.phucloctho.model.CustomerOrderDetail;
import com.tuanphan.phucloctho.model.Item;
import com.tuanphan.phucloctho.model.Price;
import com.tuanphan.phucloctho.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;
    private CustomerOrderDetailRepository orderDetailRepository;
    private PriceRepository priceRepository;
    private ItemRepository itemRepository;
    @Autowired
    public CustomerOrderService(CustomerOrderRepository repository,
                                CustomerOrderDetailRepository detailRepository,
                                PriceRepository priceRepo,
                                ItemRepository itemRepo){
        this.customerOrderRepository = repository;
        this.orderDetailRepository = detailRepository;
        this.priceRepository = priceRepo;
        this.itemRepository = itemRepo;
    }

    public List<CustomerOrder> findAll(){
        return customerOrderRepository.findAll();
    }

    @Transactional
    public CustomerOrder add(CustomerOrder customerOrder) {
        CustomerOrder addedOrder = customerOrderRepository.save(customerOrder);
        List<CustomerOrderDetail> orderDetailList = addedOrder.getCustomerOrderDetailList();
        orderDetailList.sort(Comparator.comparing(CustomerOrderDetail::getId));//to prevent deadlock
        List<Item> orderedItemList = new ArrayList<>();
        for (CustomerOrderDetail orderDetail : orderDetailList) {
            //Kiểm tra số lượng hàng tồn kho còn đủ số lượng không
            if(!checkStorageAmount(orderDetail))
                return null;
            orderDetail.setCustomerOrderId(addedOrder.getId());
            orderDetail.setTotal(calculateTotal(orderDetail));

        }
        orderDetailRepository.saveAll(orderDetailList);
        return addedOrder;
    }

    private float calculateTotal(CustomerOrderDetail orderDetail){
        Optional<Price> optionalPrice = priceRepository.findById(orderDetail.getPriceId());
        if(optionalPrice.isPresent()) {
            Price price;
            price = optionalPrice.get();
            float total;
            if (orderDetail.getDiscount() == 0)
                total = (float) price.getItemPrice() * orderDetail.getQuantity();
            else
                total = price.getItemPrice() * orderDetail.getQuantity() * (1 - orderDetail.getDiscount() / 100);
            return total;
        }
        return -1f;
    }

    private boolean checkStorageAmount(CustomerOrderDetail orderDetail){
        Optional<Item> orderedItem = itemRepository.findById(orderDetail.getItemId());
        if(!orderedItem.isPresent()){
            return false;
        }else if(orderedItem.get().getStorageAmount() < orderDetail.getQuantity())
            return false;
        return true;
    }
}
