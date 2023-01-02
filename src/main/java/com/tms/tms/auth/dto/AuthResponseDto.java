package com.tms.tms.auth.dto;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.tms.tms.auth.entity.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthResponseDto {
    private long id;

    private String uid;

    private String name;

    private String email;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public static AuthResponseDto from(User user){
        AuthResponseDto authResponseDto = new AuthResponseDto();
        BeanUtils.copyProperties(user, authResponseDto);
        return authResponseDto;
    }
}
