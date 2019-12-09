package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.dto.ItemDto;
import com.tuanphan.phucloctho.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByName(String name);

    @Query("SELECT new com.tuanphan.phucloctho.dto.ItemDto (i.id, i.name, i.unit, i.storageAmount, i.brandId, b.name) FROM Item i JOIN i.brand b")
    List<ItemDto> findAllItemDto();
}
