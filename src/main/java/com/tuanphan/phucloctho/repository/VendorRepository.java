package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
}
