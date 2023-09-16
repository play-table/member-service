package com.playtable.member.domain.response;

public record AuthResponse(
        String token,
        String redirect
) {
}
