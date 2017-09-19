package com.treino.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treino.springboot.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	List<Vendedor> findById(Integer id);
	List<Vendedor> findAll();
	
	
}
