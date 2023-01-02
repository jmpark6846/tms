package com.tms.tms.project.entity;

import com.tms.tms.auth.entity.User;
import com.tms.tms.common.BaseEntity;
import com.tms.tms.ticket.entity.Ticket;
import com.tms.tms.ticket.repository.TicketRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "project")
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name="manager_id")
    private User manager;

//    @OneToMany(mappedBy = "project")
//    private List<Ticket> tickets = new ArrayList<>();

    public Project(String name, User manager) {
        super();
        this.name = name;
        this.manager = manager;
    }

}
