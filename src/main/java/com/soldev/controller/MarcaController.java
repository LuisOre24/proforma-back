package com.soldev.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soldev.entity.Marca;
import com.soldev.service.IMarcaService;

@RestController
@RequestMapping("/v1/marca")
public class MarcaController {

	@Autowired
	private IMarcaService service;
	
	@GetMapping
	public ResponseEntity<List<Marca>> listarMarcas(){
		List<Marca> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Marca>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Marca>> listarPageable(Pageable pageable){
		Page<Marca> marcas = service.listPageable(pageable);
		return new ResponseEntity<Page<Marca>>(marcas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarMarca(@PathVariable("id") Integer id){
		Marca marca = service.obtenerUno(id);
		if(marca.getIdMarca() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(marca, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarMarca(@PathVariable("id") Integer id){
		Marca marca = service.obtenerUno(id);
		if(marca.getIdMarca() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarMarca(@RequestBody Marca entity){
		Marca marca = service.registrar(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getIdMarca()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping()
	public ResponseEntity<Object> actualizarMarca(@RequestBody Marca entity){
		Marca marca = service.actualizar(entity);
		return new ResponseEntity<Object>(marca, HttpStatus.OK);
	}
	
}
