package com.playtable.member.controller;

import com.playtable.member.domain.response.LoginResponse;
import com.playtable.member.domain.request.JoinRequest;
import com.playtable.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/check")
    public

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse join(@RequestBody JoinRequest joinRequest){
        return memberService.join(joinRequest);
    }
}
