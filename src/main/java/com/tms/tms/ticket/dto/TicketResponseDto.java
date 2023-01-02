package com.tms.tms.ticket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.entity.Ticket;
import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.BeanUtils;

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

    private User author;

    private Project project;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TicketResponseDto from(Ticket ticket){
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        BeanUtils.copyProperties(ticket, ticketResponseDto);
        return ticketResponseDto;
    }
}
