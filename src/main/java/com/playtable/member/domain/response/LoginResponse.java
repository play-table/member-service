package com.playtable.member.domain.response;

public record LoginResponse(String token) {

    public static LoginResponse of(String token){
        return new LoginResponse(token);
    }
}
