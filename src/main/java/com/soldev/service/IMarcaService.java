package com.soldev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soldev.entity.Marca;

public interface IMarcaService extends ICRUDService<Marca>{

	public Page<Marca> listPageable(Pageable pageable);
	
}
