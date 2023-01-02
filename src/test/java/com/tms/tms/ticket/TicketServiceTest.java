package com.tms.tms.ticket;

import com.tms.tms.ticket.dao.TicketDao;
import com.tms.tms.ticket.dao.TicketDaoImpl;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import com.tms.tms.ticket.service.TicketServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class TicketServiceTest {

    private final TicketDaoImpl ticketDao = Mockito.mock(TicketDaoImpl.class);
    TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);
    private TicketServiceImpl ticketService;
    @BeforeEach
    void beforeEach(){
        ticketService = new TicketServiceImpl(ticketDao, ticketRepository);
    }

    @Test
    void createTicketTest(){
        TicketDto ticketDto = TicketDto.builder()
                .title("제목")
                .content("내용")
                .build();

        Mockito.when(ticketDao.create(ticketDto))
                .thenReturn(Ticket.builder()
                        .id(123L)
                        .title("제목")
                        .content("내용")
                        .build());

        TicketResponseDto ticketResponseDto = ticketService.create(ticketDto);
        Assertions.assertEquals(ticketResponseDto.getTitle(), ticketDto.getTitle());
        verify(ticketDao).create(ticketDto);
    }
}
