package com.tms.tms.ticket.entity;

import com.tms.tms.auth.entity.User;
import com.tms.tms.common.BaseEntity;
import com.tms.tms.project.entity.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private TicketStatus status = TicketStatus.CLOSED;


    @ManyToOne
    @ToString.Exclude
    private User author;

    @ManyToOne
    @ToString.Exclude
    private Project project;
}
