package com.falcon.CostMate.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO {

    private String username;
    private String password;

    public AppUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
