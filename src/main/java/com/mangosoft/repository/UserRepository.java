package com.mangosoft.repository;

import com.mangosoft.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    void deleteByEmail(String email);
    Page<User> findAll(Pageable pageable);

}
