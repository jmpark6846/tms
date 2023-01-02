package com.tms.tms.project.controller;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.service.ProjectService;
import com.tms.tms.ticket.dto.TicketResponseDto;
import com.tms.tms.ticket.entity.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<ProjectResponseDto>> getProjectListByUser(@AuthenticationPrincipal User user, final Pageable page){
        List<ProjectResponseDto> projectResponseDtoList = projectService.getListByUser(user, page);
        return ResponseEntity.ok(projectResponseDtoList);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id){
        ProjectResponseDto projectResponseDto = projectService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(projectResponseDto);
    }

    @GetMapping("/{id}/tickets/")
    public ResponseEntity<List<TicketResponseDto>> getTicketsByProject(@AuthenticationPrincipal User user,
                                                                       @PathVariable Long id, Pageable page,
                                                                       @RequestParam TicketStatus status){
        List<TicketResponseDto> ticketResponseDtoList;

        if(status != null){
            ticketResponseDtoList = projectService.getTicketsByProject(user, id, status, page);
        }else{
            ticketResponseDtoList = projectService.getTicketsByProject(user, id, page);
        }

        return ResponseEntity.ok(ticketResponseDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<ProjectResponseDto> createProject(@Validated @RequestBody ProjectDto projectDto){
        ProjectResponseDto projectResponseDto = projectService.create(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseDto);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ProjectResponseDto> updateProject(@AuthenticationPrincipal User user, @PathVariable Long id, @Validated @RequestBody ProjectDto projectDto){
        ProjectResponseDto projectResponseDto = projectService.update(user, id, projectDto);
        return ResponseEntity.ok(projectResponseDto);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity deleteProject(@AuthenticationPrincipal User user, @PathVariable Long id){
        projectService.delete(user, id);
        return ResponseEntity.ok().build();
    }
}
