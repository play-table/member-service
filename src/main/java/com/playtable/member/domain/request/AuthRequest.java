package com.playtable.member.domain.request;

public record AuthRequest(
        String id,
        String username,
        String email
) {
}
