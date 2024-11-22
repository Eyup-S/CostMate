package com.falcon.CostMate.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Services.GroupService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/groups")
class GroupController{
	
	private GroupService groupService;
	
	@GetMapping("")
	public ResponseEntity<List<Group>> getAllGroups(){
		List<Group> groups = groupService.getAllGroups();
	    if (groups.isEmpty()) {
	        return ResponseEntity.noContent().build(); 
	    }
	    return ResponseEntity.ok(groups); 
	}
	
	@PostMapping("")
	public ResponseEntity<?> createGroup(@RequestBody AppUser user){
		groupService.createGroup(user);
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Group>> getGroupById(@PathParam("id") String id){
		Optional<Group> group = groupService.getGroupByID(id);
		if(group.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(group);
	}
	
	@GetMapping("/{id}/users")
	public ResponseEntity<List<AppUser>> getUsersOfGroup(@PathParam("id") String id){
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
	public ResponseEntity<?> addUserToGroup(@PathParam("id") String id, @RequestBody AppUser user){
		try{
			Group group = groupService.addUserToGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@GetMapping("{id}/users/requests")
	public ResponseEntity<List<AppUser>> getJoinRequests(@PathParam("id") String id){
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
	 public ResponseEntity<?> approveRequest(@PathParam("id") String id,@RequestBody AppUser user ){
		 try{
				Group group = groupService.approveRequest(id, user);
				return ResponseEntity.ok(group);
			}
			catch(Exception e) {
				return ResponseEntity.noContent().build();			
			}
	 }
	 
	 @PostMapping("{id}/users/requests/reject")
	 public ResponseEntity<?> rejectRequest(@PathParam("id") String id,@RequestBody AppUser user ){
		 try {
			Group group = groupService.approveRequest(id,user);
			return ResponseEntity.ok(group);
		 }
		 catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		 
	 }
	
	
	@PostMapping("{id}/join")
	public ResponseEntity<?> joinToGroup(@PathParam("id") String id, @RequestBody AppUser user ){
		try{
			Group group = groupService.joinToGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@PostMapping("{id}/quit")
	public ResponseEntity<?> quitFromGroup(@PathParam("id") String id, @RequestBody AppUser user ){
		try{
			Group group = groupService.quitFromGroup(id, user);
			return ResponseEntity.ok(group);
		}
		catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}
	
	
	
	
}