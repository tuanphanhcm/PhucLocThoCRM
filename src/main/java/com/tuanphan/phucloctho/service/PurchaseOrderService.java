package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.PurchaseOrder;
import com.tuanphan.phucloctho.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }
}
