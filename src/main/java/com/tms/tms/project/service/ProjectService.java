package com.tms.tms.project.service;

import com.tms.tms.project.dto.ProjectDto;
import com.tms.tms.project.dto.ProjectResponseDto;
import com.tms.tms.project.entity.Project;

public interface ProjectService {

    ProjectResponseDto get(long id);
    ProjectResponseDto create(ProjectDto projectDto);
    ProjectResponseDto update(long id, ProjectDto projectDto);
    void delete(long id);

}
