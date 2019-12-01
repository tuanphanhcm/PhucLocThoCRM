package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Integer> {
}
