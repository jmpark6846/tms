package com.tms.tms.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 6, max = 20, message = "아이디는 최소 6글자, 최대 20글자여야 합니다.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    @Size(min = 2, max = 8)
    private String name;

    @NotBlank
    @Email
    private String email;

    private String role;
}
