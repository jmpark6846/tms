package com.tms.tms.project.controller;

import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{id}/")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id){
        ProjectResponseDto projectResponseDto = projectService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(projectResponseDto);
    }

    @PostMapping("/")
    public ResponseEntity<ProjectResponseDto> createProject(@Validated @RequestBody ProjectDto projectDto){
        ProjectResponseDto projectResponseDto = projectService.create(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseDto);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @Validated @RequestBody ProjectDto projectDto){
        ProjectResponseDto projectResponseDto = projectService.update(id, projectDto);
        return ResponseEntity.ok(projectResponseDto);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity deleteProject(@PathVariable Long id){
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
