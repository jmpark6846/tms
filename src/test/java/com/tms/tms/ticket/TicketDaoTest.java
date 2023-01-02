package com.tms.tms.ticket;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.dao.TicketDaoImpl;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;


public class TicketDaoTest {

    private final TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);

    private TicketDaoImpl ticketDao;

    @BeforeEach
    void setup(){
        ticketDao = new TicketDaoImpl(ticketRepository);
    }

    @Test
    void createTicket(){
        TicketDto ticketDto = new TicketDto("제목", "없음", new User(), new Project());
        Mockito.when(ticketRepository.save(any(Ticket.class)))
                .then(returnsFirstArg());

        Ticket createdTicket = ticketDao.create(ticketDto);
        Assertions.assertEquals(createdTicket.getTitle(), ticketDto.getTitle());
        Mockito.verify(ticketRepository).save(any());
    }
}
