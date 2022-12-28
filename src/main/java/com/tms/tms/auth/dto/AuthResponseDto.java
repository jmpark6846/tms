package com.tms.tms.auth.dto;

import lombok.*;

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

    private List<String> roles = new ArrayList<>();
}
