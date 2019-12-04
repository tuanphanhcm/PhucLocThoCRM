package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "vendors")
@NoArgsConstructor
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private int id;

    @NotBlank(message = "{NotBlank.vendor.name}")
    @Length(min = 4,message = "{Length.vendor.name}")
    private String name;
    @NotBlank(message = "{NotBlank.vendor.taxCode}")
    @Length(min = 9,message = "{Length.vendor.taxCode}")
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String remarks;

    @OneToMany(mappedBy = "vendor",fetch = FetchType.LAZY)
            @JsonIgnore
    List<PurchaseOrder> purchaseOrderList;
}
