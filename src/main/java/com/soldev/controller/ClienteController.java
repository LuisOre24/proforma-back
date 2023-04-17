package com.soldev.controller;

import com.soldev.entity.Cliente;
import com.soldev.service.IClienteService;
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
@RequestMapping("/v1/cliente")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){
		List<Cliente> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Cliente>> listarPageable(Pageable pageable){
		Page<Cliente> clientes = service.listPageable(pageable);
		return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarCliente(@PathVariable("id") Integer id){
		
		Cliente cliente = service.obtenerUno(id);
		if(cliente.getIdCliente()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);
	}

	@GetMapping("search/{documento}")
	public ResponseEntity<Object> getClienteDocumento(@PathVariable("documento") String documento){

		Cliente cliente = service.getClienteDocumento(documento);

		if(cliente==null){
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarCliente(@PathVariable("id") Integer id){
		Cliente cliente = service.obtenerUno(id);
		if(cliente.getIdCliente()==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarCliente(@RequestBody Cliente entity){
		Cliente cliente = service.registrar(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping()
	public ResponseEntity<Object> actualizarMarca(@RequestBody Cliente entity){
		
		Cliente cliente = service.actualizar(entity);
		
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);
	}
	
	
}
