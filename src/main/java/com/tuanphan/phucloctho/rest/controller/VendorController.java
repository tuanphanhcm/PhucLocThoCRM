package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Vendor;
import com.tuanphan.phucloctho.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    VendorService vendorService;

    @GetMapping("")
    public Object getAllVendors(){
        List<Vendor> vendorList = vendorService.getAll();
        if(vendorList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(vendorList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object addVendor(@Valid @RequestBody Vendor vendor,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        String taxCode = vendor.getTaxCode();
        if(vendorService.existsByTaxCode(taxCode)){
            bindingResult.rejectValue("taxCode","vendor","Mã số thuế đã tồn tại");
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }

        Vendor addedVendor = vendorService.add(vendor);
        return new ResponseEntity<>(addedVendor,HttpStatus.OK);
    }

    private final int defaultPhoneNumberLength = 7;
    @GetMapping("/find/phone/{phone}")
    public Object findByPhone(@PathVariable String phone){
        if(phone.length() < defaultPhoneNumberLength)
            return new ResponseEntity<>("Số điện thoại không đúng định dạng",HttpStatus.BAD_REQUEST);
        List<Vendor> vendorList = vendorService.findByPhone(phone);
        if(vendorList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy nhà cung cấp nào!",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(vendorList,HttpStatus.OK);
    }

    @GetMapping("/find/email/{email}")
    public Object findByEmail(@PathVariable String email){
        if(email.isEmpty())
            return new ResponseEntity<>("Vui lòng nhập email",HttpStatus.BAD_REQUEST);
        if(!email.contains("@"))
            return new ResponseEntity<>("Email không đúng định dạng.",HttpStatus.BAD_REQUEST);
        List<Vendor> vendorList = vendorService.findByEmail(email);
        if(vendorList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy nhà cung cấp",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(vendorList,HttpStatus.OK);
    }

    @PutMapping("")
    public Object update(@Valid @RequestBody Vendor vendor,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        if(!vendorService.existById(vendor.getId())) {
            bindingResult.rejectValue("id", "vendor.id", "Nhà cung cấp không tồn tại.");
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Vendor updatedVendor = vendorService.update(vendor);
        return new ResponseEntity<>(updatedVendor,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable int id){
        if(!vendorService.existById(id))
            return new ResponseEntity<>("Nhà cung cấp không tồn tại.",HttpStatus.BAD_REQUEST);
        vendorService.deleteById(id);
        if(vendorService.existById(id))
            return new ResponseEntity<>("Xóa nhà cung cấp thất bại.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Xóa nhà cung cấp thành công.",HttpStatus.NO_CONTENT);
    }
}
