package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType,Integer> {
}
