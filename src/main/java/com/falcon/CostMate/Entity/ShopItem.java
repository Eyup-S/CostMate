package com.falcon.CostMate.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "ShopItems")
public class ShopItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;
	
	@NonNull
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
	private String name;
	
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
	private String amount;
	
	@ManyToOne
    @JoinColumn(name = "listName", nullable = false)
    private ListType listName;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime  createdTime;

	@ManyToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private AppUser createdBy;
	
	@NonNull
	private Boolean status;
	
    @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime boughtTime;
	
    @ManyToOne
    @JoinColumn(name = "boughtBy")
    private AppUser boughtBy; // A single user can buy this item.
}
