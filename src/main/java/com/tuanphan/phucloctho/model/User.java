package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
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
    List<PurchaseOrder> purchaseOrderList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<CustomerOrder> customerOrderList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Receipt> receiptList;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    List<Expense> expenseList;

}
