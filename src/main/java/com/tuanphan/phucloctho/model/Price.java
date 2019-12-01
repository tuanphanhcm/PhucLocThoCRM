package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue
    private int id;

    private int price;

    @Column(name = "item_id")
    private int itemId;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false, updatable = false)
    Item item;

    public Price() {
    }

    public Price(int price, int itemId, Date createDate) {
        this.price = price;
        this.itemId = itemId;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}