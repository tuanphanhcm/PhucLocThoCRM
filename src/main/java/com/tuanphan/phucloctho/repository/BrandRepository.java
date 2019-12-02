package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
