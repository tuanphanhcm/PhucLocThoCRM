package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(name = "brand_id")
    private int brandId;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    Brand brand;

    public Item() {
    }

    public Item(String name, int brandId, String unit, Brand brand) {
        this.name = name;
        this.brandId = brandId;
        this.unit = unit;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}