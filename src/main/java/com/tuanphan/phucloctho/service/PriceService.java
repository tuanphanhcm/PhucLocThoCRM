package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.dto.PriceDto;
import com.tuanphan.phucloctho.model.Price;
import com.tuanphan.phucloctho.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    private PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository repository){
        this.priceRepository = repository;
    }

    public List<PriceDto> findAllDto(){
        return priceRepository.findAllPriceDto();
    }

    public List<Price> findAll(){
        return priceRepository.findAll();
    }

    public Price add(Price price){
        return priceRepository.save(price);
    }
}
