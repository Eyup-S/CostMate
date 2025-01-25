package com.falcon.CostMate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TransactionItem> items;

    public Category() {}

    // Optional: All-args constructor for convenience
    public Category(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
