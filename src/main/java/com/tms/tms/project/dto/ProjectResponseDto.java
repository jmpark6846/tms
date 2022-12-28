package com.tms.tms.project.dto;

import com.tms.tms.auth.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
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
    private User manager;
}
