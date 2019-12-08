package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    private String name;

    @Column(name = "brand_id")
    private int brandId;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
            @JsonIgnore
    Brand brand;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
            @JsonIgnore
    List<Price> priceList;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
            @JsonIgnore
    List<PurchaseOrderDetail> purchaseOrderDetailList;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
            @JsonIgnore
    List<CustomerOrderDetail> customerOrderDetailList;

}