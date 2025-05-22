package com.falcon.CostMate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<TransactionItem> items;

    @OneToOne
    @JoinColumn(name = "groupID", referencedColumnName = "gid")
    @JsonIgnore
    private Group group;

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
