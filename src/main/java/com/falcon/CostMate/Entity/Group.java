package com.falcon.CostMate.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "Groups")
public class Group {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

	@NotNull
	private String groupName;
	
	@ManyToMany
    @JoinTable(
	    name = "group_members",
	    joinColumns = @JoinColumn(name = "gid"),
	    inverseJoinColumns = @JoinColumn(name = "uid")
    )
	@JsonIgnore
	private List<AppUser> groupMembers;
	
	@ManyToMany
    @JoinTable(
	    name = "group_membership_requests",
	    joinColumns = @JoinColumn(name = "gid"),
	    inverseJoinColumns = @JoinColumn(name = "uid")
    )
	@JsonIgnore
	private List<AppUser> groupMembershipRequests;


	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<TransactionItem> transactions = new ArrayList<>();

	public Group() {

		this.groupMembers = new ArrayList<>();
		this.groupMembershipRequests = new ArrayList<>();
	}

	public Group(@NonNull String groupName) {
		this();
		this.groupName = groupName;
	}

	public boolean isMember(AppUser user) {
		return groupMembers.contains(user);
	}

}
