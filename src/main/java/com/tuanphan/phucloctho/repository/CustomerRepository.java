package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByTaxCode(String taxCode);

    @Query("SELECT c FROM Customer c where c.name like ?1")
    List<Customer> findByName(String name);
}
