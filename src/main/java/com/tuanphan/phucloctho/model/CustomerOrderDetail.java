package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_order_details")
@Data
@AllArgsConstructor
public class CustomerOrderDetail {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "customer_order_id")
    private int customerOrderId;

    @Column(name = "item_id")
    private int itemId;

    private int quantity;

    private int price;

    private float discount;

    private float total;

    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail(int customerOrderId, int itemId, int quantity, int price, float discount, float total) {
        this.customerOrderId = customerOrderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}

