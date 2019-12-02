package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;
    @NotNull
    private String name;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String remarks;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    List<Receipt> receiptList;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    List<CustomerOrder> customerOrderList;

}
