package com.soldev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soldev.entity.Usuario;
import com.soldev.repo.IUsuarioRepo;
import com.soldev.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;

	@Override
	public Usuario register(Usuario usuario) {
		return repo.save(usuario);
	}
	
	
	
	
}
