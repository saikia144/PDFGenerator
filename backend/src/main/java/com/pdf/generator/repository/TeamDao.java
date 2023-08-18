package com.pdf.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdf.generator.model.Team;

@Repository
public interface TeamDao extends JpaRepository<Team, Integer>{
	Team findByTid(Integer tid);
}
