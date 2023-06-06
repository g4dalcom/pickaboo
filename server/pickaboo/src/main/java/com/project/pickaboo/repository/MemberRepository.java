package com.project.pickaboo.repository;

import com.project.pickaboo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
    Boolean existsByUsername(String username);
}
