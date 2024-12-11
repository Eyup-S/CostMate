package com.falcon.CostMate.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
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

	@NonNull
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

	@ManyToOne
	@JoinColumn(name = "admin_user", nullable = false)
	@JsonIgnore
	private AppUser adminUser;

	public Group() {

		this.groupMembers = new ArrayList<>();
		this.groupMembershipRequests = new ArrayList<>();
	}

	public Group(@NonNull String groupName) {
		this();
		this.groupName = groupName;
	}


}
