package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brands")
@Data
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    Set<Item> items;

    public Brand() {
    }

    public Brand(String name, String country, Set<Item> items) {
        this.name = name;
        this.country = country;
        this.items = items;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}