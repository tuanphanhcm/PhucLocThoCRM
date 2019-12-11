package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
            @JsonIgnore
    PurchaseOrder purchaseOrder;

    @Column(name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false,updatable = false)
            @JsonIgnore
    Item item;
    @NotNull
    private int quantity;

    @Column(name = "price_id")
    private int priceId;
    @ManyToOne
    @JoinColumn(name = "price_id",insertable = false,updatable = false)
            @JsonIgnore
    Price price;

    @NotNull
    private float discount;
    @NotNull
    private float total;

}
