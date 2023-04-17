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

import com.soldev.entity.Categoria;
import com.soldev.service.ICategoriaService;

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {

	@Autowired
	private ICategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Categoria>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Categoria>> listarPageable(Pageable pageable){
		Page<Categoria> categorias = service.listPageable(pageable);
		return new ResponseEntity<Page<Categoria>>(categorias, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarCategoria(@PathVariable("id") Integer id){
		
		Categoria categoria = service.obtenerUno(id);
		if(categoria.getIdCategoria()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(categoria, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarCategoria(@PathVariable("id") Integer id){
		Categoria categoria = service.obtenerUno(id);
		if(categoria.getIdCategoria()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarCategoria(@RequestBody Categoria entity){
		Categoria categoria = service.registrar(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getIdCategoria()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping()
	public ResponseEntity<Object> actualizarMarca(@RequestBody Categoria entity){
		
		Categoria categoria = service.actualizar(entity);
		
		return new ResponseEntity<Object>(categoria, HttpStatus.OK);
	}
	
	
}
