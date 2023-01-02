package com.tms.tms.project.dto;

import com.tms.tms.auth.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProjectDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    private long manager_id;
}
