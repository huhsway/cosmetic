package com.example.common.member.api;

import com.example.common.member.api.dto.MemberJoinRequestDto;
import com.example.common.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long save
            (@RequestBody MemberJoinRequestDto memberJoinRequestDto)
    { return memberService.save(memberJoinRequestDto); }


}