package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer_order_details")
public class CustomerOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    @Column(name = "customer_order_id")
    private int customerOrderId;
    @ManyToOne
    @JoinColumn(name = "customer_order_id",insertable = false,updatable = false)
            @JsonIgnore
    CustomerOrder customerOrder;

    @Column(name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false,updatable = false)
            @JsonIgnore
    Item item;

    @Min(value = 1)
    private int quantity;

    @Column(name = "price_id")
    private int priceId;
    @ManyToOne
    @JoinColumn(name = "price_id",insertable = false,updatable = false)
            @JsonIgnore
    Price price;

    @Min(value = 0)
    private float discount;
    @Min(value = 0)
    private float total;

}

