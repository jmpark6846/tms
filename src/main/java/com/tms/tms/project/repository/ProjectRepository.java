package com.tms.tms.project.repository;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.entity.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Query("select p from Project p where p.manager = :manager")
//    List<Project> findAllByManager(@Param("manager") User manager, Pageable page);
    List<Project> findAllByManager(User manager);

    List<Project> findAllByManager(User manager, Pageable page);

}
