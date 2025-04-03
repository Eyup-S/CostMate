package com.falcon.CostMate.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO {

    private String username;
    private String password;
    private Integer icon;

    public AppUserDTO(String username, String password, Integer icon) {
        this.username = username;
        this.password = password;
        this.icon = icon;
    }


}
