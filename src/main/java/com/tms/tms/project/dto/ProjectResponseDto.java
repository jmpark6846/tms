package com.tms.tms.project.dto;

import com.tms.tms.auth.dto.AuthResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectResponseDto {
    private Long id;
    private String name;
    private AuthResponseDto manager;
}
