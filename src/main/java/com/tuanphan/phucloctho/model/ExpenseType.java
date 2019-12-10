package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "expenses_type")
public class ExpenseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",insertable = false,updatable = false)
    private int id;

    @NotBlank
    private String name;

    private String description;

    @OneToMany(mappedBy = "expenseType",fetch = FetchType.LAZY)
            @JsonIgnore
    List<Expense> expenseList;

}
