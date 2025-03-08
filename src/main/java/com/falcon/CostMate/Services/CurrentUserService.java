package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authenticated, the principal might be "anonymousUser"
        if (authentication == null || !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("No authenticated user found.");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof AppUser) {
            return (AppUser) principal;
        } else {
            // In case you're using a custom UserDetails implementation, adjust this block accordingly.
            throw new RuntimeException("Principal is not an instance of AppUser: " + principal);
        }
    }
}
