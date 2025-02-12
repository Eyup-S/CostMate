package com.falcon.CostMate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "UserAddedCategory")
public class UserAddedCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group categoryGroup;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser createdBy;

    @OneToMany(mappedBy = "userAddedCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TransactionItem> items;

    public UserAddedCategory() {}

    // All-args constructor (including cid)
    public UserAddedCategory(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    // Constructor that doesn't set cid
    public UserAddedCategory(String name) {
        this.name = name;
    }
}