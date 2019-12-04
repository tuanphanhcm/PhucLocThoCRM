package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "{NotBlank.customer.name}")
    @Length(min = 4,message = "{Length.customer.name}")
    private String name;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String remarks;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
            @JsonIgnore
    List<Receipt> receiptList;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
            @JsonIgnore
    List<CustomerOrder> customerOrderList;

}
