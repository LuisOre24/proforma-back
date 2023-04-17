package com.soldev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soldev.entity.Producto;
import com.soldev.repo.IProductoRepo;
import com.soldev.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoRepo repo; 
	
	@Override
	public Producto registrar(Producto entity) {
		return repo.save(entity);
	}

	@Override
	public Producto actualizar(Producto entity) {
		return repo.save(entity);
	}

	@Override
	public List<Producto> obtenerTodo() {
		return repo.findAll();
	}

	@Override
	public Producto obtenerUno(Integer id) {
		Optional<Producto> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<String> search(String keyword) {
			return repo.search(keyword);
	}

	@Override
	public Page<Producto> listPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	
}
