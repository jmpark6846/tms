package com.tms.tms.ticket.dao;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket read(Long id) {
        return ticketRepository.getReferenceById(id);
    }

    @Override
    public Ticket create(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .title(ticketDto.getTitle())
                .content(ticketDto.getContent())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setTitle(ticketDto.getTitle());
        ticket.setContent(ticketDto.getContent());
        Ticket savedTicket = ticketRepository.save(ticket);
        return savedTicket;
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
