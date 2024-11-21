package com.falcon.CostMate.Controllers;

import com.falcon.CostMate.Services.LoginService;
import com.falcon.CostMate.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falcon.CostMate.Entity.AppUser;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user){
        System.out.println("user " + user.getUsername() + " " + user.getPassword());
        return loginService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user){

        return loginService.register(user);
    }
    
    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AppUser user){
    	try {
            token = token.substring(7); // Remove "Bearer " prefix
            String username = jwtUtil.extractUsername(token);

            AppUser userDetails = loginService.loadUserByUsername(username);
            if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
                return ResponseEntity.ok("Valid token");
            } else {
                return ResponseEntity.status(401).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }
    	
    	
    }

}
