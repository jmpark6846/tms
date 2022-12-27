package com.tms.tms.ticket.dto;

import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketResponseDto {
    private Long id;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
