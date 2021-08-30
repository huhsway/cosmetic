package com.example.common.member.api;

import com.example.common.member.api.dto.MemberJoinRequestDto;
import com.example.common.member.domain.MemberRole;
import com.example.common.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberApiControllerTest {

    @Autowired
    MemberApiController memberApiController;

    @Autowired
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void joinMember() throws Exception {
        //given
        MemberJoinRequestDto joinDto = MemberJoinRequestDto.builder()
                .email("email@gmail.com")
                .password("password")
                .name("John")
                .role(MemberRole.USER)
                .build();

        //when & then
        this.mockMvc.perform(post("/api/v1/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}