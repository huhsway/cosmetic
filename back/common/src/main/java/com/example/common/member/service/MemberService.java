package com.example.common.member.service;


import com.example.common.member.api.dto.MemberJoinRequestDto;
import com.example.common.member.api.dto.MemberRequestDto;
import com.example.common.member.api.dto.MemberResponseDto;
import com.example.common.member.domain.Member;
import com.example.common.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long save(MemberJoinRequestDto dto) {
        validateDuplicateMember(dto.getEmail());
        Member member = dto.toEntity();
        member.encodingPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }

    public Member memberLogin(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail()).get();
        if (!(passwordEncoder.matches(memberRequestDto.getPassword(), member.getPassword()))) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        } else {
            return member;
        }
    }


    public List<MemberResponseDto> findMembers() {
        return memberRepository.findAll()
                .stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    private void validateDuplicateMember(String email) {
        List<Member> findMembers = memberRepository.findAllByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public MemberResponseDto findOne(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new MemberResponseDto(member);
    }

    public MemberResponseDto findByEmail(String email) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new MemberResponseDto(findMember);
    }

}