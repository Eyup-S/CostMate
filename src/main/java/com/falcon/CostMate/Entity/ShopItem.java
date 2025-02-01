package com.falcon.CostMate.Entity;

import java.util.Date;

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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
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
	
	@NotNull
	private String name;
	
	private String description;


	@Temporal(TemporalType.TIMESTAMP)
	private Date  createdTime;

	@ManyToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private AppUser createdBy;
	
	private Boolean status;
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date boughtTime;
	
    @ManyToOne
    @JoinColumn(name = "boughtBy")
    private AppUser boughtBy; // A single user can buy this item.
}
