package com.terror.springcommunity.model.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInDto {
    private String username;
    private String password;
}
