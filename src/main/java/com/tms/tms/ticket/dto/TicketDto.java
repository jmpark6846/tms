package com.tms.tms.ticket.dto;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TicketDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    private String content;

    private User author;

    @NotNull
    private Project project;
}
