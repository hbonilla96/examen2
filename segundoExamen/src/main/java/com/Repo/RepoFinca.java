package com.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelos.Finca;

public interface RepoFinca extends JpaRepository<Finca, Long>{
	Finca findTopByOrderByIdDesc();
}
