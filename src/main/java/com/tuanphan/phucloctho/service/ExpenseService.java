package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Expense;
import com.tuanphan.phucloctho.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository repository){
        this.expenseRepository = repository;
    }

    public List<Expense> findAll(){
        return expenseRepository.findAll();
    }

    public Expense add(Expense expense){
        return expenseRepository.save(expense);
    }

}
