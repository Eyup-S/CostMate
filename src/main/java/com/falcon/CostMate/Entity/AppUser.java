package com.falcon.CostMate.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Data
@Table(name = "app_user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Getter
    @Setter
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify if you want to track account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify if you want to track account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify if you want to track credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify if you want to track whether the user is enabled
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


}
