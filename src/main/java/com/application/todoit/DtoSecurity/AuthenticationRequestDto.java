package com.application.todoit.DtoSecurity;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String username;
    private String password;

}
