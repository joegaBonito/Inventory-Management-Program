package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.Member;


@Repository
public interface MemberRepository extends CrudRepository<Member,Long> {

	List<Member> findAllByOrderByEmail();

	Member findByEmail(String username);

}
