package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {

    Vendor findByTaxCode(@Param("taxCode") String taxCode);
    List<Vendor> findByEmail(@Param("email")String email);
    List<Vendor> findByPhone(String phone);
}
