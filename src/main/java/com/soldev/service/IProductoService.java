package com.soldev.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soldev.entity.Producto;

public interface IProductoService extends ICRUDService<Producto>{

	public List<String> search(String keyword);
	
	Page<Producto> listPageable(Pageable pageable);
	
}
