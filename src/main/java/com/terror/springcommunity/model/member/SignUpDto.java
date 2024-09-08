package com.terror.springcommunity.model.member;

import com.terror.springcommunity.constans.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private String author;
    private UserRoleEnum role = UserRoleEnum.USER;
}
