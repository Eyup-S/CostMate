package com.falcon.CostMate.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "Groups")
public class Group {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
	
	@ManyToMany
    @JoinTable(
	    name = "group_members",
	    joinColumns = @JoinColumn(name = "gid"),
	    inverseJoinColumns = @JoinColumn(name = "uid")
    )
    private List<AppUser> groupMembers;
	
	@ManyToMany
    @JoinTable(
	    name = "group_membership_requests",
	    joinColumns = @JoinColumn(name = "gid"),
	    inverseJoinColumns = @JoinColumn(name = "uid")
    )
    private List<AppUser> groupMembershipRequests;
	
	private AppUser adminUser;
	
}
