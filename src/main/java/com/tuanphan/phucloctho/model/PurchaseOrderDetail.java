package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchase_order_details")
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    @Column(name = "purchase_order_id")
    private int purchaseOrderId;
    @ManyToOne
    @JoinColumn(name = "purchase_order_id",insertable = false,updatable = false)
    PurchaseOrder purchaseOrder;

    @Column(name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false,updatable = false)
    Item item;

    private int quantity;

    private int price;

    private float discount;

    private float total;

}
