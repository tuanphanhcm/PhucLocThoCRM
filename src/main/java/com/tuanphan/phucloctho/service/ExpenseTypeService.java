package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.ExpenseType;
import com.tuanphan.phucloctho.repository.ExpenseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTypeService {
    private ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    public ExpenseTypeService(ExpenseTypeRepository repository){
        this.expenseTypeRepository = repository;
    }

    public List<ExpenseType> expenseTypeList(){
        return expenseTypeRepository.findAll();
    }

    public ExpenseType add(ExpenseType expenseType){
        return expenseTypeRepository.save(expenseType);
    }

    public List<ExpenseType> findByName(String name){
        return expenseTypeRepository.findByName(name);
    }

    public List<ExpenseType> findAll() {
        return expenseTypeRepository.findAll();
    }

    public boolean existsById(int id) {
        return expenseTypeRepository.existsById(id);
    }

    public boolean deleteById(int id) {
        if(expenseTypeRepository.existsById(id)){
            expenseTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
