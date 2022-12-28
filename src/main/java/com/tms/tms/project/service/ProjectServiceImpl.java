package com.tms.tms.project.service;

import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.entity.Project;
import com.tms.tms.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponseDto get(long id) {
        Project project = projectRepository.getReferenceById(id);
        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                project.getId(),
                project.getName(),
                project.getManager());

        return projectResponseDto;
    }

    @Override
    public ProjectResponseDto create(ProjectDto projectDto) {
        Project project = Project.builder()
                .name(projectDto.getName())
                .manager(projectDto.getManager())
                .build();

        Project savedProject = projectRepository.save(project);
        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                savedProject.getId(),
                savedProject.getName(),
                savedProject.getManager());
        return projectResponseDto;
    }

    @Override
    public ProjectResponseDto update(long id, ProjectDto projectDto) {
        Project project = projectRepository.getReferenceById(id);
        project.setName(projectDto.getName());
        project.setManager(projectDto.getManager());
        Project savedProject = projectRepository.save(project);

        ProjectResponseDto projectResponseDto = new ProjectResponseDto(
                savedProject.getId(),
                savedProject.getName(),
                savedProject.getManager());
        return projectResponseDto;
    }

    @Override
    public void delete(long id) {
        projectRepository.deleteById(id);
    }
}
