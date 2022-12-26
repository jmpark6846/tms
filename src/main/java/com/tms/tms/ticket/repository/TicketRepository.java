package com.tms.tms.ticket.repository;

import com.tms.tms.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TicketRepository extends JpaRepository<Ticket, Long>, QuerydslPredicateExecutor {

}
