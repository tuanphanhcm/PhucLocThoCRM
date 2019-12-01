package com.tuanphan.phucloctho.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "expenses_type")
@Data
@AllArgsConstructor
public class ExpenseType {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String name;

    private String description;

    @OneToMany(mappedBy = "expenseType",fetch = FetchType.LAZY)
    Set<Expense> expenses;

    public ExpenseType() {
    }

    public ExpenseType(@NotBlank String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
