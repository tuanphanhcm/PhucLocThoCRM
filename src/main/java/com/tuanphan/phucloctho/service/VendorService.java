package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Vendor;
import com.tuanphan.phucloctho.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    VendorRepository vendorRepository;

    public List<Vendor> getAll(){
        return vendorRepository.findAll();
    }
    public  Vendor add(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public Vendor update(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public void deleteById(int id){
        vendorRepository.deleteById(id);
    }

    public Boolean existsByTaxCode(String taxCode){
        Vendor vendor = vendorRepository.findByTaxCode(taxCode);
        if(vendor == null)
            return false;
        return true;
    }

    public Boolean existById(int id){
        return vendorRepository.existsById(id);
    }

    public List<Vendor> findByPhone(String phone){
        return vendorRepository.findByPhone(phone);
    }

    public List<Vendor> findByEmail(String email){
        return vendorRepository.findByEmail(email);
    }

    public List<Vendor> add(List<Vendor> vendorList) {
        return vendorRepository.saveAll(vendorList);
    }
}
