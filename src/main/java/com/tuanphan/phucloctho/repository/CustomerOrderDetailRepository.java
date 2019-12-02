package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.CustomerOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail,Integer> {
}
