package com.tms.tms.ticket.dao;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.entity.Ticket;

public interface TicketDao {
    public Ticket read(Long id);

    public Ticket create(TicketDto ticketDto);

    public Ticket update(Long id, TicketDto ticketDto);

    public void delete(Long id);

}
