package com.tms.tms.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketDto {
    private String title;
    private String content;

}
