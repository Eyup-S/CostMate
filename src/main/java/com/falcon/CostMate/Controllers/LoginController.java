package com.falcon.CostMate.Controllers;

import com.falcon.CostMate.DTO.AppUserDTO;
import com.falcon.CostMate.Repositories.AppUserRepository;
import com.falcon.CostMate.Services.LoginService;
//import com.falcon.CostMate.utils.JwtUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falcon.CostMate.Entity.AppUser;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;
    //private JwtUtil jwtUtil;
    private final AppUserRepository userRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<AppUser> login(@RequestBody AppUserDTO user){
        try{
            return ResponseEntity.ok(loginService.login(user));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/auth/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUserDTO user){
        try{
            return ResponseEntity.ok(loginService.register(user));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }
    
    @PostMapping("/auth/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AppUser user){
    	try {
            token = token.substring(7); // Remove "Bearer " prefix
            /*String username = jwtUtil.extractUsername(token);

            AppUser userDetails = loginService.loadUserByUsername(username);
            if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
                return ResponseEntity.ok("Valid token");
            } else {
                return ResponseEntity.status(401).body("Invalid token");
            }*/
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }
        return ResponseEntity.ok("Valid token");

    }


    @GetMapping("/users/{username}")
    public ResponseEntity<AppUser> getUserByName(@PathVariable("username") String username){
        AppUser user = loginService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }

}
