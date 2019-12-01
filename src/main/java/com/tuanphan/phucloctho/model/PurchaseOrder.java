package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "purchase_orders")
@Data
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(name = "vendor_id")
    private int vendorId;
    @ManyToOne
    @JoinColumn(name = "vendor_id",insertable = false,updatable = false)
    Vendor vendor;

    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    User user;

    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.LAZY)
    Set<PurchaseOrderDetail> purchaseOrderDetails;

    private String billNumber;



    public PurchaseOrder() {
    }

    public PurchaseOrder(Date createDate, int vendorId, int userId, String billNumber) {
        this.createDate = createDate;
        this.vendorId = vendorId;
        this.userId = userId;
        this.billNumber = billNumber;
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

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
