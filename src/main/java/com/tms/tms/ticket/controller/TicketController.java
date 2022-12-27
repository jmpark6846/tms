package com.tms.tms.ticket.controller;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/{id}/")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable Long id){
        TicketResponseDto ticketResponseDto = ticketService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

    @PostMapping("/")
    public ResponseEntity<TicketResponseDto> createTicket(@Validated @RequestBody TicketDto ticketDto){
        TicketResponseDto ticketResponseDto = ticketService.create(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponseDto);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable Long id, @Validated @RequestBody TicketDto ticketDto){
        TicketResponseDto ticketResponseDto = ticketService.update(id, ticketDto);
        return ResponseEntity.ok(ticketResponseDto);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity deleteTicket(@PathVariable Long id){
        ticketService.delete(id);
        return ResponseEntity.ok().build();
    }
}
