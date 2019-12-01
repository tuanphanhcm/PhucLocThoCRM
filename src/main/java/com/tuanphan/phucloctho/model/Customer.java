package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String name;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String remarks;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    Set<Receipt> receipts;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    Set<CustomerOrder> customerOrders;

    public Customer(@NotNull String name, String taxCode, String address, String phone, @Email String email, String remarks) {
        this.name = name;
        this.taxCode = taxCode;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.remarks = remarks;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
