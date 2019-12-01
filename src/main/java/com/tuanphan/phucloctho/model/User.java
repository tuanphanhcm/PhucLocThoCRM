package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String username;

    private String password;

    private String phone;

    @Column(name = "role_id")
    private String roleId;
    @ManyToOne
    @JoinColumn(name = "role_id",insertable = false,updatable = false)
    Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    Set<PurchaseOrder> purchaseOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Set<CustomerOrder> customerOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Set<Receipt> receipts;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    Set<Expense> expenses;

    public User() {
    }

    public User(@NotNull String name, @NotBlank String username, @NotBlank String password, String phone, String roleId) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.roleId = roleId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
