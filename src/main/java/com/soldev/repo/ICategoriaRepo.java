package com.soldev.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.soldev.entity.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Integer>{

}
