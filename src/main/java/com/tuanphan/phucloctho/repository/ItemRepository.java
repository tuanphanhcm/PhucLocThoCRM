package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByName(String name);
}
