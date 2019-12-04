package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(value = {"role","purchaseOrderList","customerOrderList","receiptList","expenseList"},
        allowGetters = true)
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
    private int roleId;
    @ManyToOne
    @JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "fk_user_role"),insertable = false,updatable = false)
    @JsonIgnore
    Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    List<PurchaseOrder> purchaseOrderList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    List<CustomerOrder> customerOrderList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    List<Receipt> receiptList;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Expense> expenseList;

}
