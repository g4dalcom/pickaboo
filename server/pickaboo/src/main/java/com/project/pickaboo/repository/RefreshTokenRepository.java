package com.project.pickaboo.repository;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Boolean existsByMember_Id(Long id);
}
