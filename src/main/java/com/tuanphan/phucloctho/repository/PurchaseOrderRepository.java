package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Integer> {
}
