package com.tms.tms.auth.repository;

import com.tms.tms.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
}
