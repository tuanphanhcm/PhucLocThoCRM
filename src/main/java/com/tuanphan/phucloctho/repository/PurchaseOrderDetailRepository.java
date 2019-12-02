package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.PurchaseOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail,Integer> {
}
