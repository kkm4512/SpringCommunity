package com.terror.springcommunity.model.member;

import com.terror.springcommunity.constans.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private String author;
    private UserRoleEnum role;
}
