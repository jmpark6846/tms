package com.tms.tms.project;

import com.tms.tms.auth.entity.User;
import com.tms.tms.project.entity.Project;
import com.tms.tms.project.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ProjectRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    ProjectRepository projectRepository;

    User user;

    @BeforeEach
    void before(){
        user = new User("joon123", "password", "joonmo", "joon@joon.co");
        User user2 = new User("kim", "password", "joonmo", "kim@joon.co");
        em.persist(user);
        em.persist(user2);
        ArrayList<Project> list = new ArrayList<>();

        for(int i=0; i<10; i++){
            Project project = new Project("프로젝트"+i, user);
            list.add(project);
        }

        list.add(new Project("프로젝트99", user2));
        projectRepository.saveAll(list);

    }
    @Test
    void findAllByManager(){
        Pageable page = PageRequest.of(0, 10, Sort.by("name").descending());
        List<Project> list = projectRepository.findAllByManager(user, page);

        Assertions.assertEquals(10, list.size());
    }
}
