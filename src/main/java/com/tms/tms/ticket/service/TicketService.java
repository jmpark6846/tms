package com.tms.tms.ticket.service;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Service;


public interface TicketService {
    TicketResponseDto read(Long id);

    TicketResponseDto create(TicketDto ticketDto);

    TicketResponseDto update(Long id, TicketDto ticketDto);

    void delete(Long id);

}
