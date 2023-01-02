package com.tms.tms.ticket.service;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.dao.TicketDao;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.entity.TicketStatus;
import com.tms.tms.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketDao ticketDao;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, TicketRepository ticketRepository){
        this.ticketDao = ticketDao;
        this.ticketRepository = ticketRepository;
    }

    private TicketResponseDto createTicketResponseDto(Ticket ticket){
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .content(ticket.getContent())
                .project(ticket.getProject())
                .author(ticket.getAuthor())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }



    @Override
    public TicketResponseDto get(Long id) {

        Ticket ticket = ticketDao.get(id);

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
