package com.tms.tms.ticket.dao;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.entity.Ticket;

public interface TicketDao {
    Ticket get(Long id);

    Ticket create(TicketDto ticketDto);

    Ticket update(Long id, TicketDto ticketDto);

    void delete(Long id);

}
