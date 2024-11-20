package com.falcon.CostMate.Controllers;

import com.falcon.CostMate.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falcon.CostMate.Entity.AppUser;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

  //  private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user){
        System.out.println("user " + user.getUsername() + " " + user.getPassword());
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user){

        return userService.register(user);
    }

}
