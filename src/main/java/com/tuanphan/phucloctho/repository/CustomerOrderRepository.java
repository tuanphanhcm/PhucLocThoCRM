package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer> {
}
