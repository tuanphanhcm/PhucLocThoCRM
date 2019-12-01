package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.util.Date;

@Entity
@Table(name = "receipts")
@Data
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(name = "customer_id")
    private int customerId;
    @ManyToOne
    @JoinColumn(name = "customer_id",insertable = false,updatable = false)
    Customer customer;

    private int amount;

    private String remarks;

    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    User user;

    public Receipt() {
    }

    public Receipt(Date createDate, int customerId, int amount, String remarks, int userId) {
        this.createDate = createDate;
        this.customerId = customerId;
        this.amount = amount;
        this.remarks = remarks;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
