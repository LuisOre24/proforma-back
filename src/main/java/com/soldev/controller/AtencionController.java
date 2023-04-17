package com.soldev.controller;

import com.soldev.entity.Atencion;
import com.soldev.service.IAtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/atencion")
public class AtencionController {

	@Autowired
	private IAtencionService service;
	
	@GetMapping
	public ResponseEntity<List<Atencion>> listarAtencions(){
		List<Atencion> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Atencion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Atencion>> listarPageable(Pageable pageable){
		Page<Atencion> atenciones = service.listPageable(pageable);
		return new ResponseEntity<Page<Atencion>>(atenciones, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarAtencion(@PathVariable("id") Integer id){
		
		Atencion atencion = service.obtenerUno(id);
		if(atencion.getIdAtencion()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(atencion, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarAtencion(@PathVariable("id") Integer id){
		Atencion atencion = service.obtenerUno(id);
		if(atencion.getIdAtencion()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarAtencion(@RequestBody Atencion entity){
		Atencion atencion = service.registrar(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atencion.getIdAtencion()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping()
	public ResponseEntity<Object> actualizarMarca(@RequestBody Atencion entity){
		
		Atencion atencion = service.actualizar(entity);
		
		return new ResponseEntity<Object>(atencion, HttpStatus.OK);
	}
	
	
}
