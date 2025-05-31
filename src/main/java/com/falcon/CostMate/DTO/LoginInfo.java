package com.falcon.CostMate.DTO;

import com.falcon.CostMate.Entity.AppUser;

public record LoginInfo(AppUser user,
                        String token) {
}
