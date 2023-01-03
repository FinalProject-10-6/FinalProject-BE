package com.pj106.project_10_6.repository;

import com.pj106.project_10_6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
