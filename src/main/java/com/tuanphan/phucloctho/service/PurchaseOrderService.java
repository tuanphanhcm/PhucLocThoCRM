package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Price;
import com.tuanphan.phucloctho.model.PurchaseOrder;
import com.tuanphan.phucloctho.model.PurchaseOrderDetail;
import com.tuanphan.phucloctho.repository.PriceRepository;
import com.tuanphan.phucloctho.repository.PurchaseOrderDetailRepository;
import com.tuanphan.phucloctho.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    private PurchaseOrderDetailRepository orderDetailRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private PriceRepository priceRepository;
    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository repository,
                                PurchaseOrderDetailRepository detailRepository,
                                PriceRepository priceRepo){
        this.purchaseOrderRepository = repository;
        this.orderDetailRepository = detailRepository;
        this.priceRepository = priceRepo;
    }
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Transactional
    public PurchaseOrder add(PurchaseOrder purchaseOrder) {
        PurchaseOrder addedOrder = purchaseOrderRepository.save(purchaseOrder);
        List<PurchaseOrderDetail> orderDetailList = addedOrder.getPurchaseOrderDetailList();
        orderDetailList.sort(Comparator.comparing(PurchaseOrderDetail::getId));//to prevent deadlock
        for (PurchaseOrderDetail item : orderDetailList) {
            item.setPurchaseOrderId(addedOrder.getId());
            Optional<Price> price = priceRepository.findById(item.getPriceId());
            if(price.isPresent()) {
                float total = calculateTotal(price.get(),item);
                item.setTotal(total);
            }
        }
        orderDetailRepository.saveAll(orderDetailList);
        return addedOrder;
    }

    private float calculateTotal(Price price, PurchaseOrderDetail orderDetail){
        float total;
        if(orderDetail.getDiscount() == 0)
            total = (float)price.getItemPrice() * orderDetail.getQuantity();
        else
            total = price.getItemPrice() * orderDetail.getQuantity() * (1 - orderDetail.getDiscount()/100);
        return total;
    }
}
