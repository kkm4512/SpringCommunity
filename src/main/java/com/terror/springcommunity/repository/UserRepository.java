package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);
}
