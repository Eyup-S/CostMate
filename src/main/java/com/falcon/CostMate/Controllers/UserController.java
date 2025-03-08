package com.falcon.CostMate.Controllers;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping("/current-user")
    public ResponseEntity<AppUser> getCurrentUser() {
        AppUser currentUser = currentUserService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
}
