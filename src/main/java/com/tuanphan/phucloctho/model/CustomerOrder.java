package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_orders")
@Data
@AllArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "user_id")
    private int userId;

    private String invoiceNumber;

    public CustomerOrder() {
    }

    public CustomerOrder(Date createDate, int customerId, int userId, String invoiceNumber) {
        this.createDate = createDate;
        this.customerId = customerId;
        this.userId = userId;
        this.invoiceNumber = invoiceNumber;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}

