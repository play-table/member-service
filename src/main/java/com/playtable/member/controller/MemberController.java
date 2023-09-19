package com.playtable.member.controller;

import com.playtable.member.config.MemberTokenInfo;
import com.playtable.member.domain.request.AuthRequest;
import com.playtable.member.domain.request.JoinRequest;
import com.playtable.member.domain.response.AuthResponse;
import com.playtable.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse check(@RequestBody AuthRequest authRequest){
        return memberService.check(authRequest);
    }

    @GetMapping("/test")
    public MemberTokenInfo test(@AuthenticationPrincipal MemberTokenInfo memberTokenInfo){
        return memberTokenInfo;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse join(
            @AuthenticationPrincipal MemberTokenInfo memberTokenInfo,
            @RequestBody JoinRequest joinRequest
    ){
        return memberService.join(memberTokenInfo, joinRequest);
    }
}
