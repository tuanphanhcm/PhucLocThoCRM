package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Expense;
import com.tuanphan.phucloctho.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService service){
        this.expenseService = service;
    }

    @GetMapping("")
    public Object findAll(){
        List<Expense> expenseList = expenseService.findAll();
        if(expenseList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(expenseList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody Expense expense,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        Expense addedExpense = expenseService.add(expense);
        if(addedExpense == null)
            return new ResponseEntity<>("Thêm phiếu chi thất bại.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(addedExpense,HttpStatus.OK);
    }
}
