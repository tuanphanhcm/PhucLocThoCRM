package com.tuanphan.phucloctho.model;

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
@Table(name = "customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "customer_id")
    private int customerId;
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    Customer customer;

    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    List<CustomerOrderDetail> customerOrderDetailList;

    private String invoiceNumber;
}