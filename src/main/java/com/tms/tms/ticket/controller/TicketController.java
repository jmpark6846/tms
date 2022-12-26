package com.tms.tms.ticket.controller;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/")
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketDto ticketDto){
        TicketResponseDto ticketResponseDto = ticketService.create(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponseDto);
    }
}
