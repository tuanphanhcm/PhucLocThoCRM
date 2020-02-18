package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Brand;
import com.tuanphan.phucloctho.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService service){
        this.brandService = service;
    }

    @GetMapping("")
    public Object findAll(){
        List<Brand> brandList = brandService.findAll();
        if(brandList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(brandList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object add(@Valid @RequestBody Brand brand,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        Brand addedBrand = brandService.save(brand);
        return new ResponseEntity<>(addedBrand,HttpStatus.OK);
    }

    @PutMapping("")
    public Object update(@Valid @RequestBody Brand brand,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        if(!brandService.existsById(brand.getId())){
            bindingResult.rejectValue("id","brand.id","Nhãn hàng không tồn tại.");
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Brand updatedBrand = brandService.update(brand);
        if(updatedBrand == null)
            return new ResponseEntity<>("Cập nhật nhãn hàng thất bại.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(updatedBrand,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id){
        if(brandService.delete(id))
            return new ResponseEntity<>("Xóa nhãn hàng thành công.",HttpStatus.OK);
        return new ResponseEntity<>("Xóa nhãn hàng thất bại.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/name/{name}")
    public Object findByName(@PathVariable String name){
        List<Brand> brandList = brandService.findByName(name);
        if(brandList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy nhãn hàng.",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(brandList,HttpStatus.OK);
    }

    @GetMapping("/country/{country}")
    public Object findByCountry(@PathVariable String country){
        List<Brand> brandList = brandService.findByCountry(country);
        if(brandList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy nhãn hàng.",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(brandList,HttpStatus.OK);
    }
}
