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
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupID")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "addedBy")
    private AppUser  addedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  addedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  boughtDate;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shares> shares = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_added_category_id")
    private UserAddedCategory userAddedCategory;

    private Boolean isMoneyTransfer;

}
