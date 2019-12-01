package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "purchase_order_details")
@Data
@AllArgsConstructor
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "purchase_order_id")
    private int purchaseOrderId;

    @Column(name = "item_id")
    private int itemId;

    private int quantity;

    private int price;

    private float discount;

    private float total;

    public PurchaseOrderDetail() {
    }

    public PurchaseOrderDetail(int purchaseOrderId, int itemId, int quantity, int price, float discount, float total) {
        this.purchaseOrderId = purchaseOrderId;
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

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
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
