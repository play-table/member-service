package com.playtable.member.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @Getter
public class Member {

    @Id
    private UUID id;
    private String username;
    private String email;

    private String realName;
    private String contact;
    private Role role;
    private String nickName;
    private String profileImage;

}
