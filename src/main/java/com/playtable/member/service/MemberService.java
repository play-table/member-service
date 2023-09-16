package com.playtable.member.service;

import com.playtable.member.config.JwtService;
import com.playtable.member.config.MemberTokenInfo;
import com.playtable.member.domain.entity.Member;
import com.playtable.member.domain.entity.Role;
import com.playtable.member.domain.request.AuthRequest;
import com.playtable.member.domain.request.JoinRequest;
import com.playtable.member.domain.response.AuthResponse;
import com.playtable.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public AuthResponse join(MemberTokenInfo memberTokenInfo, JoinRequest joinRequest) {
        Member member = memberRepository.save(
                joinRequest.toEntity(
                        memberTokenInfo.getId(),
                        memberTokenInfo.getUsername(),
                        memberTokenInfo.getEmail()
                ));
        return new AuthResponse(
                jwtService.generateToken(member),
                getRedirect(member)
        );
    }

    public AuthResponse check(AuthRequest authRequest) {

        UUID id = UUID.fromString(authRequest.id());
        String username = authRequest.username();
        String email = authRequest.email();

        Member member = memberRepository
                .findByIdAndUsernameAndEmail(id, username, email)
                .orElseGet(()-> memberRepository.save(
                        Member.builder()
                                .id(id)
                                .username(username)
                                .email(email)
                                .role(Role.MEMBER)
                                .build()));

        return new AuthResponse(
                jwtService.generateToken(member),
                getRedirect(member)
        );
    }

    private String getRedirect(Member member){
        return switch (member.getRole()){
            case CUSTOMER -> "/main";
            case OWNER -> "/owner";
            default -> "/signup";
        };
    }
}
