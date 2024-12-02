package com.falcon.CostMate.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Data
@Table(name = "TransactionItems")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    
    @NonNull
    private String name;

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
        joinColumns = @JoinColumn(name = "iid"),
        inverseJoinColumns = @JoinColumn(name = "uid")
    )
    private Set<AppUser> paidBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  addedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  boughtDate;

    private Double price;

    @ManyToMany
    @JoinTable(
	    name = "item_shared_with",
	    joinColumns = @JoinColumn(name = "iid"),
	    inverseJoinColumns = @JoinColumn(name = "uid")
    )
    private List<AppUser> sharedWith;

}
