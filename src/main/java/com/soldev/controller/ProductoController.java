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

import com.soldev.entity.Producto;
import com.soldev.service.IProductoService;


@RestController
@RequestMapping("/v1/producto")
public class ProductoController {

	
	@Autowired
	private IProductoService service;
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<String>> search(@PathVariable("keyword") String keyword){
		List<String> key = service.search(keyword);
		return new ResponseEntity<List<String>>(key, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(){
		List<Producto> lista = service.obtenerTodo(); 
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarProducto(@PathVariable("id") Integer id){
		Producto producto = service.obtenerUno(id);
		if(producto.getIdProducto() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(producto, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Producto>> listarPageable(Pageable pageable){
		Page<Producto> productos = service.listPageable(pageable);
		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarProducto(@PathVariable("id") Integer id){
		Producto producto = service.obtenerUno(id);
		if(producto.getIdProducto() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping()
	public ResponseEntity<Object> registrarProducto(@RequestBody Producto entity){
		Producto producto = service.registrar(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producto.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping()
	public ResponseEntity<Object> actualizarProducto(@RequestBody Producto entity){
		Producto producto = service.actualizar(entity);
		return new ResponseEntity<Object>(producto, HttpStatus.OK);
	}
	
	
}
