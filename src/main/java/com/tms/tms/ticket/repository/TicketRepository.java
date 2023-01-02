package com.tms.tms.ticket.repository;

import com.tms.tms.project.entity.Project;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.entity.TicketStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>, QuerydslPredicateExecutor {
    List<Ticket> findByProject(Project project, Pageable page);
    List<Ticket> findByProjectAndStatus(Project project, TicketStatus status, Pageable page);
}
