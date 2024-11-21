    package com.falcon.CostMate.Entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.NotNull;
    import lombok.Data;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.List;

    @Entity
    @Data
    @Getter
    @Setter
    @Table(name = "app_user")
    public class AppUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long uid;

        @NotNull
        @NotEmpty
        private String username;

        @NotNull
        @NotEmpty
        private String password;

        @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<TransactionItem> addedItems;

        @ManyToMany(mappedBy = "paidBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<TransactionItem> paidItems;


    }
