package com.playtable.member.service;

import com.playtable.member.domain.request.JoinRequest;
import com.playtable.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void join(JoinRequest joinRequest) {
        memberRepository.save(joinRequest.toEntity());
    }
}
