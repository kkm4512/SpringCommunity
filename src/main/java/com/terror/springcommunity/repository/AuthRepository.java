package com.terror.springcommunity.repository;

import com.terror.springcommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Member,Long> {
}
