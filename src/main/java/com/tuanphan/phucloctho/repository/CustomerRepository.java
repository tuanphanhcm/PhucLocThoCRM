package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.Customer;
import javafx.scene.control.CustomMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByTaxCode(String taxCode);

}
