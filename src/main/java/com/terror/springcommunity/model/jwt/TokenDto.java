package com.terror.springcommunity.model.jwt;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}
