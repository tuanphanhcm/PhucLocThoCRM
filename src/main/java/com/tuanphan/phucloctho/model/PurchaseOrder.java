package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "vendor_id")
    private int vendorId;
    @ManyToOne
    @JoinColumn(name = "vendor_id",insertable = false,updatable = false)
    @JsonIgnore
    Vendor vendor;

    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @JsonIgnore
    User user;

    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.LAZY)
    List<PurchaseOrderDetail> purchaseOrderDetailList;

    private String billNumber;
}
