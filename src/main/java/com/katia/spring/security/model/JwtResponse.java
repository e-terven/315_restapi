package com.katia.spring.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private final String token;
    private final Long userId;

    public JwtResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public Long getUserId() {
        return this.userId;
    }
}

