package com.example.common.member.service;

import com.example.common.member.api.dto.MemberJoinRequestDto;
import com.example.common.member.api.dto.MemberResponseDto;
import com.example.common.member.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void joinMemberAndFind() throws Exception {
        //given
        MemberJoinRequestDto joinDto = MemberJoinRequestDto.builder()
                .email("email@gmail.com")
                .password("password")
                .name("John")
                .role(MemberRole.USER)
                .build();
        //when
        Long savedId = memberService.save(joinDto);
        MemberResponseDto findMemberDto = memberService.findOne(savedId);

        //then
        assertThat(findMemberDto.getEmail()).isEqualTo(joinDto.getEmail());
        assertThat(findMemberDto.getName()).isEqualTo(joinDto.getName());
        assertThat(findMemberDto.getRole()).isEqualTo(joinDto.getRole());
        assertThat(findMemberDto.getId()).isEqualTo(savedId);
    }

    @Test
    void FindNonExistMember() throws Exception {
        //when & then
        assertThatThrownBy(() -> memberService.findOne(20L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 유저가 존재하지 않습니다.");
    }

}