package com.tms.tms.ticket;

import com.tms.tms.config.SecurityConfiguration;
import com.tms.tms.ticket.controller.TicketController;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.google.gson.Gson;

import java.time.LocalDateTime;

@WebMvcTest(TicketController.class)
@ContextConfiguration(classes = { SecurityConfiguration.class, })
public class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TicketServiceImpl ticketService;

    @Test
    public void createTest() throws Exception {
        TicketDto ticketDto = new TicketDto("제목", "내용");
        given(ticketService.create(ticketDto))
                .willReturn(new TicketResponseDto(123L, "제목", "내용", LocalDateTime.now(), LocalDateTime.now()));

        Gson gson = new Gson();
        String content = gson.toJson(ticketDto);

        mockMvc.perform(
                post("/api/tickets/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(ticketService).create(ticketDto);
    }
}
