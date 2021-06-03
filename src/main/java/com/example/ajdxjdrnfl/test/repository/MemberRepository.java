package com.example.ajdxjdrnfl.test.repository;

import com.example.ajdxjdrnfl.test.entitiy.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

}
