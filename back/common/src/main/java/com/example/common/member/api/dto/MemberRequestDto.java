package com.example.common.member.api.dto;

import com.example.common.member.domain.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    @Builder
    public MemberRequestDto(String email, String password, String name, MemberRole role) {
        this.email = email;
        this.password = password;
    }

}
