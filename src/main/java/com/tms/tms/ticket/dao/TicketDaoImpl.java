package com.tms.tms.ticket.dao;

import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TicketDaoImpl implements TicketDao {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketDaoImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket get(Long id) {
        return ticketRepository.getReferenceById(id);
    }

    @Override
    public Ticket create(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .title(ticketDto.getTitle())
                .content(ticketDto.getContent())
                .author(ticketDto.getAuthor())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.getReferenceById(id);
        System.out.println(ticket);
        ticket.setTitle(ticketDto.getTitle());
        ticket.setContent(ticketDto.getContent());
        ticket.setAuthor(ticketDto.getAuthor());
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
