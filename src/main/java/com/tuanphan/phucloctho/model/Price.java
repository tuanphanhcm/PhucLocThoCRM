package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @NotNull
    @Min(value = 1)
    private int itemPrice;

    @Column(name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false, updatable = false)
            @JsonIgnore
    Item item;

    @OneToMany(mappedBy = "price",fetch = FetchType.LAZY)
    @JsonIgnore
    List<PurchaseOrderDetail> purchaseOrderDetailList;

    @OneToMany(mappedBy = "price",fetch = FetchType.LAZY)
    @JsonIgnore
    List<CustomerOrderDetail> customerOrderDetailList;

}