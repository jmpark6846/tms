package com.tms.tms.project.service;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.TicketStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectResponseDto> getListByUser(User user, Pageable page);

    ProjectResponseDto get(long id);

    List<TicketResponseDto> getTicketsByProject(User user, Long id, Pageable page);
    List<TicketResponseDto> getTicketsByProject(User user, Long id, TicketStatus status, Pageable page);

    ProjectResponseDto create(ProjectDto projectDto);
    ProjectResponseDto update(User user, long id, ProjectDto projectDto);
    void delete(User user, long id);

}
