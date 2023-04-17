package com.soldev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldev.entity.Usuario;
import com.soldev.service.IUsuarioService;

@RestController
@RequestMapping("/v1/auth/admin")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> createUser(@RequestBody Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setEnabled(true);
		service.register(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
}
