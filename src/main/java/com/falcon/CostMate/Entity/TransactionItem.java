package com.falcon.CostMate.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Data
@Table(name = "TransactionItems")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    @NotNull
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupID")
    @ToString.Exclude
    private Group group;

    @ManyToOne
    @JoinColumn(name = "addedBy")
    private AppUser addedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  addedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  boughtDate;

    private Double price;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shares> shares = new ArrayList<>();

    private String amount;

    private Boolean isMoneyTransfer;

    private Boolean isBought;
}
