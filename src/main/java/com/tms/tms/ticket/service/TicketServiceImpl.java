package com.tms.tms.ticket.service;

import com.tms.tms.ticket.dao.TicketDao;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketDao ticketDao;

    @Autowired
    TicketServiceImpl(TicketDao ticketDao){
        this.ticketDao = ticketDao;
    }

    private TicketResponseDto createTicketResponseDto(Ticket ticket){
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .content(ticket.getContent())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }
    @Override
    public TicketResponseDto read(Long id) {
        Ticket ticket = ticketDao.read(id);
        return createTicketResponseDto(ticket);
    }

    @Override
    public TicketResponseDto create(TicketDto ticketDto) {
        Ticket ticket = ticketDao.create(ticketDto);
        return createTicketResponseDto(ticket);
    }

    @Override
    public TicketResponseDto update(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketDao.update(id, ticketDto);
        return createTicketResponseDto(ticket);
    }

    @Override
    public void delete(Long id) {
        ticketDao.delete(id);
    }
}
