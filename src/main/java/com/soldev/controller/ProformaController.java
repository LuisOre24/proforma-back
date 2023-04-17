package com.soldev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.soldev.entity.Proforma;
import com.soldev.service.IProformaService;

@RestController
@RequestMapping("/v1/proforma")
public class ProformaController {

	@Autowired
	private IProformaService service;
	
	@GetMapping
	public ResponseEntity<List<Proforma>> listarProformas(){
		List<Proforma> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Proforma>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> listarProforma(@PathVariable("id") Integer id){
		Proforma proforma = service.obtenerUno(id);
		if(proforma.getIdProforma() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(proforma, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Proforma>> listarPageable(Pageable pageable){
		Page<Proforma> proformas = service.listPageable(pageable);
		return new ResponseEntity<Page<Proforma>>(proformas, HttpStatus.OK);
	}
	
	@PostMapping(value = "/exportarpdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = "application/json")
	public ResponseEntity<byte[]> exportarPdf(@RequestBody Proforma proforma){
		byte[] data = null;
		data = service.generarProforma(proforma);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarProforma(@PathVariable("id") Integer id){
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarProforma(@RequestBody Proforma entity){
		Proforma proforma = service.registrar(entity);
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proforma.getIdProforma()).toUri();
		//return ResponseEntity.created(location).build();
		return new ResponseEntity<Object>(proforma, HttpStatus.CREATED);
	}
	
}
