package com.playtable.member.domain.request;

import com.playtable.member.domain.entity.Member;
import com.playtable.member.domain.entity.Role;

import java.util.UUID;

public record JoinRequest(
        String realName,
        String contact,
        String role,
        String nickName,
        String profileImage
) {

    public Member toEntity(UUID id, String username, String email){
        return Member.builder()
                .id(id)
                .username(username)
                .email(email)
                .realName(realName)
                .contact(contact)
                .role(Role.valueOf(role))
                .nickName(nickName)
                .profileImage(profileImage)
                .build();
    }
}
