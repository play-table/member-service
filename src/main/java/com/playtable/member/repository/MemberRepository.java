package com.playtable.member.repository;

import com.playtable.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByIdAndUsernameAndEmail(UUID id, String username, String email);
}
