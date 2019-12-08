package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Brand;
import com.tuanphan.phucloctho.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    public Brand save(Brand brand){
        return brandRepository.save(brand);
    }

    public boolean delete(int id){
        if(brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Brand update(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> findByName(String  name){
        return brandRepository.findByName(name);
    }

    public List<Brand> findByCountry(String country){
        return brandRepository.findByCountry(country);
    }

    public boolean existsById(int id){
        return brandRepository.existsById(id);
    }
}
