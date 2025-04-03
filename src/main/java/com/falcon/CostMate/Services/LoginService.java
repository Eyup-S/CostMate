package com.falcon.CostMate.Services;

import com.falcon.CostMate.DTO.AppUserDTO;
import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Repositories.AppUserRepository;
//import com.falcon.CostMate.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final JwtUtil jwtUtil;

    // Registration
    public AppUser register(AppUserDTO user) throws Exception{
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken!");
        }
        AppUser dbUser = new AppUser();
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        dbUser.setUsername(user.getUsername());
        dbUser.setIcon(user.getIcon());
        System.out.println("User being saved: " + user);


        return userRepository.save(dbUser);
    }


    public AppUser login(AppUserDTO user) throws Exception {
        Optional<AppUser> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()) {
            throw new Exception("User Not Found!");
        }

        AppUser dbUser = optionalUser.get();
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new Exception("Wrong password!");
        }

        //String token = jwtUtil.generateToken(dbUser.getUsername());
        //return ResponseEntity.ok().body(token);
        return optionalUser.get();
    }

    // UserDetailsService Implementation for Spring Security
    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    // Helper method to parse roles to Spring Security authorities
    /*
    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }*/

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

