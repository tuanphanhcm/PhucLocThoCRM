package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.ExpenseType;
import com.tuanphan.phucloctho.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expense-type")
public class ExpenseTypeController {
    private ExpenseTypeService expenseTypeService;

    @Autowired
    public ExpenseTypeController(ExpenseTypeService service){
        this.expenseTypeService = service;
    }

    @GetMapping("")
    public Object findAll(){
        List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
        if(expenseTypeList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(expenseTypeList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody ExpenseType expenseType,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        ExpenseType addedExpenseType = expenseTypeService.add(expenseType);
        return new ResponseEntity<>(addedExpenseType,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable int id){
        if(!expenseTypeService.existsById(id))
            return new ResponseEntity<>("Loại chi phí không tồn tại.",HttpStatus.BAD_REQUEST);
        if(expenseTypeService.deleteById(id))
            return new ResponseEntity<>("Xóa loại chi phí thành công.",HttpStatus.OK);
        return new ResponseEntity<>("Xóa loại chi phí thất bại.",HttpStatus.BAD_REQUEST);
    }
}
