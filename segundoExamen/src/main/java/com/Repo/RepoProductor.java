package com.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelos.Productor;

public interface RepoProductor extends JpaRepository<Productor, Long>{

	List<Productor> findByFirstnameContaining(String name);
}
