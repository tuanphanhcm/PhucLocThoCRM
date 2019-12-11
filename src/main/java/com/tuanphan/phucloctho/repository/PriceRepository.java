package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.dto.PriceDto;
import com.tuanphan.phucloctho.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price,Integer> {

    @Query("SELECT new com.tuanphan.phucloctho.dto.PriceDto(p.id, p.createDate, i.name, p.itemPrice, p.type) FROM Price p JOIN p.item i")
    List<PriceDto> findAllPriceDto();
}
