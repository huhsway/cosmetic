package com.example.common.member.api.dto;

import com.example.common.member.domain.Member;
import com.example.common.member.domain.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private MemberRole role;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.role = member.getRole();
    }
}