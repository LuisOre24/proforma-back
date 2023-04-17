package com.soldev.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soldev.entity.Marca;

public interface IMarcaRepo extends JpaRepository<Marca, Integer>{

}
