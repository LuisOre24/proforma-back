package com.soldev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soldev.entity.Categoria;

public interface ICategoriaService extends ICRUDService<Categoria>{

	public Page<Categoria> listPageable(Pageable pageable);
	
}
