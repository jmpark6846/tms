package com.tms.tms.project.service;

import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.entity.User;
import com.tms.tms.auth.repository.UserRepository;
import com.tms.tms.common.exception.Forbidden;
import com.tms.tms.common.exception.NotAuthenticated;
import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.entity.Project;
import com.tms.tms.project.repository.ProjectRepository;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.entity.TicketStatus;
import com.tms.tms.ticket.repository.TicketRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, TicketRepository ticketRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<ProjectResponseDto> getListByUser(User user, Pageable pageParam) {
        Pageable page = pageParam;

        if( user == null ){
            throw new RuntimeException("사용자가 존재하지 않습니다.");
        }
        if( pageParam == null){
            page = PageRequest.of(0, 10);
        }
        List<Project> list = projectRepository.findAllByManager(user, page);
        List<ProjectResponseDto> projectResponseDtoList = new ArrayList<>();
        for(Project p: list){
            projectResponseDtoList.add(new ProjectResponseDto(p.getId(), p.getName(), AuthResponseDto.from(p.getManager())));
        }
        return projectResponseDtoList;
    }

    @Override
    public ProjectResponseDto get(long id) {
        Project project = projectRepository.getReferenceById(id);
        User manager = project.getManager();

        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                project.getId(),
                project.getName(),
                AuthResponseDto.builder()
                        .id(manager.getId())
                        .uid(manager.getUid())
                        .email(manager.getEmail())
                        .name(manager.getName())
                        .roles(manager.getRoles())
                        .build());
        return projectResponseDto;
    }

    @Override
    public List<TicketResponseDto> getTicketsByProject(User user, Long id, Pageable page) {
        List<TicketResponseDto> list = new ArrayList<>();
        if(user == null){
            throw new NotAuthenticated();
        }

        Project project = projectRepository.getReferenceById(id);
        if(project.getManager() != user){
            throw new Forbidden();
        }

        List<Ticket> tickets = ticketRepository.findByProject(project, page);
        for(Ticket t: tickets){
            list.add(TicketResponseDto.from(t));
        }
        return list;
    }

    @Override
    public List<TicketResponseDto> getTicketsByProject(User user, Long id, TicketStatus status, Pageable page) {
        if(user == null){
            throw new NotAuthenticated();
        }

        Project project = projectRepository.getReferenceById(id);
        if(project.getManager() != user){
            throw new Forbidden();
        }


        List<Ticket> list = ticketRepository.findByProjectAndStatus(project, status, page);
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();

        for(Ticket ticket: list){
            ticketResponseDtoList.add(TicketResponseDto.from(ticket));
        }
        return ticketResponseDtoList;
    }



    @Override
    public ProjectResponseDto create(ProjectDto projectDto) {
        User manager = userRepository.getReferenceById(projectDto.getManager_id());
        Project project = Project.builder()
                .name(projectDto.getName())
                .manager(manager)
                .build();

        Project savedProject = projectRepository.save(project);
        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                savedProject.getId(),
                savedProject.getName(),
                AuthResponseDto.builder()
                        .id(manager.getId())
                        .uid(manager.getUid())
                        .email(manager.getEmail())
                        .name(manager.getName())
                        .roles(manager.getRoles())
                .build());
        return projectResponseDto;
    }

    @Override
    public ProjectResponseDto update(User user, long id, ProjectDto projectDto) {
        Project project = projectRepository.getReferenceById(id);
        if(project.getManager() != user){
            throw new Forbidden();
        }

        project.setName(projectDto.getName());
        User manager = userRepository.getReferenceById(projectDto.getManager_id());
        project.setManager(manager);
        Project savedProject = projectRepository.save(project);

        manager = savedProject.getManager();

        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                savedProject.getId(),
                savedProject.getName(),
                AuthResponseDto.builder()
                        .id(manager.getId())
                        .uid(manager.getUid())
                        .email(manager.getEmail())
                        .name(manager.getName())
                        .roles(manager.getRoles())
                        .build());
        return projectResponseDto;
    }

    @Override
    public void delete(User user, long id) {
        Project project = projectRepository.getReferenceById(id);
        if(project.getManager() != user){
            throw new Forbidden();
        }
        projectRepository.deleteById(id);
    }
}
