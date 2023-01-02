package com.tms.tms.ticket.service;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.TicketStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface TicketService {


    TicketResponseDto get(Long id);


    TicketResponseDto create(TicketDto ticketDto);

    TicketResponseDto update(Long id, TicketDto ticketDto);

    void delete(Long id);

}
