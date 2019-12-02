package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendors")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    private String name;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String remarks;

    @OneToMany(mappedBy = "vendor",fetch = FetchType.LAZY)
    List<PurchaseOrder> purchaseOrderList;
}
