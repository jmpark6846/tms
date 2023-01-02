package com.tms.tms.project;

import com.tms.tms.auth.entity.User;
import com.tms.tms.auth.repository.UserRepository;
import com.tms.tms.common.exception.Forbidden;
import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.entity.Project;
import com.tms.tms.project.repository.ProjectRepository;
import com.tms.tms.project.service.ProjectServiceImpl;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.entity.TicketStatus;
import com.tms.tms.ticket.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.Manager;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DataJpaTest
public class ProjectServiceTest {

    ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);

    ProjectServiceImpl projectService;

    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    private User user;
    private User user2;
    Project projectByUser2;

    @BeforeEach
    void before(){
        em = testEntityManager.getEntityManager();
        projectService = new ProjectServiceImpl(projectRepository, userRepository, ticketRepository);

        user = new User("joon123", "password", "joonmo", "joon@joon.co");
        user2 = new User("kim", "password", "kim", "kim@joon.co");
        em.persist(user);
        em.persist(user2);
    }


    @Test
    void exceptionThrownWhenDeleteProjectByNotManager(){
        Project project = new Project(123L,"이름", user);
        Mockito.when(projectRepository.getReferenceById(123L))
                .thenReturn(project);

        Assertions.assertThrows(Forbidden.class, ()->{
            projectService.delete(user2, project.getId());
        });
    }

    @Test
    void getTickets(){
        Project project = new Project(123L,"이름", user);

        Mockito.when(projectRepository.getReferenceById(123L))
                .thenReturn(project);

        List<Ticket> list = new ArrayList<>();
        for(int i=0; i<5; i++){
            list.add(Ticket.builder()
                    .id(1L+i)
                    .title("제목"+i)
                    .content("내용"+i)
                    .author(user)
                    .status(i == 0 ? TicketStatus.CLOSED : TicketStatus.OPEN)
                    .project(project)
                    .build());
        }
        Mockito.when(ticketRepository.findByProject(project, PageRequest.of(0,10)))
                .thenReturn(list);

        List<TicketResponseDto> ticketResponseDtoList = projectService.getTicketsByProject(user, project.getId(), PageRequest.of(0,10));
        Assertions.assertEquals(list.size(), ticketResponseDtoList.size());
    }
}
