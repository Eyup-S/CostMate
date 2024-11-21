/*package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest req;

    public ResponseEntity<String> register(AppUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username already taken!", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("Registration successful!", HttpStatus.OK);
    }

    public ResponseEntity<String> login(AppUser user) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            AppUser dbUser = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                dbUser.setPassword(null);
                req.getSession().setAttribute("customer", dbUser);
                return new ResponseEntity<>("Login successful!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    // UserDetailsService Implementation for Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            AppUser appUser = optionalUser.get();
            return new User(
                    appUser.getUsername(),
                    appUser.getPassword(),
                    true,  // Account is enabled
                    true,  // Account is not expired
                    true,  // Credentials are not expired
                    true,  // Account is not locked
                    new ArrayList<>()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    // Helper method to parse roles to Spring Security authorities
    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
*/