package com.soldev.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldev.entity.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
	
}
