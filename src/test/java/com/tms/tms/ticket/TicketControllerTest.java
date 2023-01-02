package com.tms.tms.ticket;

import com.tms.tms.auth.entity.User;
import com.tms.tms.config.security.SecurityConfiguration;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.controller.TicketController;
import com.tms.tms.ticket.dto.TicketDto;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.google.gson.Gson;

import java.time.LocalDateTime;


@WebMvcTest
@Import(TicketController.class)
@ContextConfiguration(classes = { SecurityConfiguration.class, })
public class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TicketServiceImpl ticketService;

    @Test
    public void getTicket() throws Exception {
        User user = User.builder().build();
        Project project = Project.builder().build();
        given(ticketService.get(123L))
                .willReturn(new TicketResponseDto(123L, "제목", "내용", user, project, LocalDateTime.now(), LocalDateTime.now()));

        String ticketId = "123";

        mockMvc.perform(
                        get("/api/tickets/"+ticketId+"/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(123L))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists())
                .andDo(print());

        verify(ticketService).get(123L);
    }

    @Test
    public void createTicket() throws Exception {
        TicketDto ticketDto = new TicketDto("제목", "내용", new User(), new Project());
        given(ticketService.create(ticketDto))
                .willReturn(new TicketResponseDto(123L, "제목", "내용", new User(), new Project(), LocalDateTime.now(), LocalDateTime.now()));

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

    @Test
    public void updateTicket() throws Exception {
        TicketDto ticketDto = new TicketDto("문제", "해결해주세요",  new User(), new Project());

        given(ticketService.update(123L, ticketDto))
                .willReturn(new TicketResponseDto(123L, "문제", "해결해주세요",  new User(), new Project(), LocalDateTime.now(), LocalDateTime.now()));

        Gson gson = new Gson();
        String content = gson.toJson(ticketDto);
        mockMvc.perform(put("/api/tickets/123/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("해결해주세요"));

        verify(ticketService).update(123L, ticketDto);
    }

    @Test
    public void deleteTicket() throws Exception {

        doNothing().when(ticketService).delete(isA(Long.class));
        mockMvc.perform(
                        delete("/api/tickets/123/"))
                .andExpect(status().isOk());

        verify(ticketService).delete(123L);
    }

}
