package com.tuanphan.phucloctho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private int id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
            @JsonIgnore
    List<User> userList;
}
