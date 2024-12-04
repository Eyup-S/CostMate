package com.falcon.CostMate.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Services.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app/groups")
class GroupController{
	
	private final GroupService groupService;
	
	@GetMapping("/")
	public ResponseEntity<List<Group>> getAllGroups(){
		List<Group> groups = groupService.getAllGroups();
	    if (groups.isEmpty()) {
	        return ResponseEntity.noContent().build(); 
	    }
	    return ResponseEntity.ok(groups); 
	}
	
	@PostMapping("/{groupName}")
	public ResponseEntity<?> createGroup(@RequestBody AppUser user, @PathVariable("groupName") String groupName){
		System.out.println("groupname: " + groupName);
		Group group = groupService.createGroup(user, groupName);
		return ResponseEntity.ok(group);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Group>> getGroupById(@PathVariable("id") String id){
		Optional<Group> group = groupService.getGroupByID(id);
		if(group.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(group);
	}
	
	@GetMapping("/{id}/users")
	public ResponseEntity<List<AppUser>> getUsersOfGroup(@PathVariable("id") String id){
		try {			
			List<AppUser> users = groupService.getUsersOfGroup(id);
			if(users.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(users);
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/users")
	public ResponseEntity<?> addUserToGroup(@PathVariable("id") String id, @RequestBody AppUser user){
		try{
			Group group = groupService.addUserToGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@GetMapping("{id}/users/requests")
	public ResponseEntity<List<AppUser>> getJoinRequests(@PathVariable("id") String id){
		try {			
			List<AppUser> users = groupService.getJoinRequests(id);
			if(users.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(users);
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	 @PostMapping("{id}/users/requests/approve")
	 public ResponseEntity<?> approveRequest(@PathVariable("id") String id,@RequestBody AppUser user ){
		 try{
				Group group = groupService.approveRequest(id, user);
				return ResponseEntity.ok(group);
			}
			catch(Exception e) {
				return ResponseEntity.noContent().build();			
			}
	 }
	 
	 @PostMapping("{id}/users/requests/reject")
	 public ResponseEntity<?> rejectRequest(@PathVariable("id") String id,@RequestBody AppUser user ){
		 try {
			Group group = groupService.approveRequest(id,user);
			return ResponseEntity.ok(group);
		 }
		 catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		 
	 }
	
	
	@PostMapping("{id}/join")
	public ResponseEntity<?> joinToGroup(@PathVariable("id") String id, @RequestBody AppUser user ){
		try{
			Group group = groupService.joinToGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@PostMapping("{id}/quit")
	public ResponseEntity<?> quitFromGroup(@PathVariable("id") String id, @RequestBody AppUser user ){
		try{
			Group group = groupService.quitFromGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	
	
	
}