package com.tms.tms.project;

import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.entity.User;
import com.tms.tms.config.TestConfiguration;
import com.tms.tms.project.controller.ProjectController;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(ProjectController.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:application.properties")
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectServiceImpl projectService;

    User user;

    @BeforeEach
    void beforeEach(){
        user = User.builder()
                .id(123L)
                .uid("user1")
                .password("password")
                .email("user@user.co")
                .name("joon").build();
    }

    @Test
    void UnAuthenticatedAccess_throwException() throws Exception {
        mockMvc.perform(
                get("/api/projects/"))
                .andExpect(status().is(403));

    }

    @Test
    void getListByUser() throws Exception{
        List<ProjectResponseDto> list = new ArrayList<>();
        for(long i=0; i<3; i++){
            list.add(new ProjectResponseDto(i, "project"+i, AuthResponseDto.from(user)));
        }

        given(projectService.getListByUser(user, PageRequest.of(0, 3)))
                .willReturn(list);

        mockMvc.perform(
            get("/api/projects/?page=0&size=3")
                .with(user(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());

        verify(projectService).getListByUser(user, PageRequest.of(0, 3));

    }
}
