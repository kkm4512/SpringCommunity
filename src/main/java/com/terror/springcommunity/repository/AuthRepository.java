package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Member,Long> {
}
