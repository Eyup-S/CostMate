package com.falcon.CostMate.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Data
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category category;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "addedBy")
    private AppUser  addedBy;

    @ManyToMany
    @JoinTable(
            name = "item_paid_by",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> paidBy;

    @Temporal(TemporalType.TIMESTAMP)
    private String addedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private String boughtDate;

    private Double price;

    @ManyToMany
    @JoinTable(
            name = "item_shared_with",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> sharedWith;

}
