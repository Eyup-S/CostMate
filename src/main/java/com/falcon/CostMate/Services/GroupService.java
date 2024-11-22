package com.falcon.CostMate.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Repositories.GroupRepository;
import com.falcon.CostMate.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
	
	private GroupRepository groupRepository;
	private UserRepository userRepository;
	
	public List<Group> getAllGroups(){
		return groupRepository.findAll();
		
	}
	
	public Group createGroup(AppUser adminuser) {
		Group group = new Group();
		group.setAdminUser(adminuser);
		group.getGroupMembers().add(adminuser);
		return groupRepository.save(group);
	}
	
	public Optional<Group> getGroupByID(String id) {
		Optional<Group> group = groupRepository.findById(Long.parseLong(id));
		return group;
	}
	
	public List<AppUser> getUsersOfGroup(String id){
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));

        if (groupOpt.isPresent()) {
            return groupOpt.get().getGroupMembers();
        }
        throw new RuntimeException("Group or User not found");
	}
	
	public Group addUserToGroup(String id, AppUser addedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(addedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();

            // Add user to groupMembers if not already present
            if (!group.getGroupMembers().contains(user)) {
                group.getGroupMembers().add(user);
            }

            return groupRepository.save(group);
        }

        throw new RuntimeException("Group or User not found");
	}
	
	public Group removeUserFromGroup(String id, AppUser addedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(addedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();

            if (group.getGroupMembers().contains(user)) {
                group.getGroupMembers().remove(user);
            }

            return groupRepository.save(group);
        }

        throw new RuntimeException("Group or User not found");
	}
	
	
	public List<AppUser> getJoinRequests(String id){
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));

        if (groupOpt.isPresent()) {
            return groupOpt.get().getGroupMembershipRequests();
        }
        throw new RuntimeException("Group not found");
	}
	
	
	public Group joinToGroup(String id, AppUser requestedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(requestedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();
            if(!group.getGroupMembershipRequests().contains(user)){
            	group.getGroupMembershipRequests().add(user);
            }
            return groupRepository.save(group);
        }
        throw new RuntimeException("Group not found");

	}
	
	public Group approveRequest(String id, AppUser requestedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(requestedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();
            if(group.getGroupMembershipRequests().contains(user)){
            	group.getGroupMembershipRequests().remove(user);
            	group.getGroupMembers().add(user);
            }
            return groupRepository.save(group);
        }
        
        throw new RuntimeException("Group or request not found");
	}
	
	public Group rejectRequest(String id, AppUser requestedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(requestedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();
            if(group.getGroupMembershipRequests().contains(user)){
            	group.getGroupMembershipRequests().remove(user);
            }
            return groupRepository.save(group);
        }
        
        throw new RuntimeException("Request or group not found");
	}
	
	
	public Group quitFromGroup(String id, AppUser requestedUser) {
		Optional<Group> groupOpt = groupRepository.findById(Long.parseLong(id));
        Optional<AppUser> userOpt = userRepository.findById(requestedUser.getId());

        if (groupOpt.isPresent() && userOpt.isPresent()) {
            Group group = groupOpt.get();
            AppUser user = userOpt.get();
            if(group.getGroupMembers().contains(user)){
            	group.getGroupMembers().remove(user);
            }
            return groupRepository.save(group);
        }
        throw new RuntimeException("Request or group not found");
	}
	
	
}
