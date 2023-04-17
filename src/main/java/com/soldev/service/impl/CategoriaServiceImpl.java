package com.soldev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soldev.entity.Categoria;
import com.soldev.repo.ICategoriaRepo;
import com.soldev.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired
	private ICategoriaRepo repo;

	@Override
	public Categoria registrar(Categoria entity) {
		return repo.save(entity);
	}

	@Override
	public Categoria actualizar(Categoria entity) {
		return repo.save(entity);
	}

	@Override
	public List<Categoria> obtenerTodo() { 
		return repo.findAll();
	}

	@Override
	public Categoria obtenerUno(Integer id) {
		Optional<Categoria> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Categoria();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public Page<Categoria> listPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	

}
