package com.soldev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soldev.entity.Marca;
import com.soldev.repo.IMarcaRepo;
import com.soldev.service.IMarcaService;

@Service
public class MarcaServiceImpl implements IMarcaService{

	@Autowired
	private IMarcaRepo repo;
	
	@Override
	public Marca registrar(Marca entity) {
		return repo.save(entity);
	}

	@Override
	public Marca actualizar(Marca entity) {
		return repo.save(entity);
	}

	@Override
	public List<Marca> obtenerTodo() {
		return repo.findAll();
	}

	@Override
	public Marca obtenerUno(Integer id) {
		Optional<Marca> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Marca();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public Page<Marca> listPageable(Pageable pageable) {		
		return repo.findAll(pageable);
	}

	
}
