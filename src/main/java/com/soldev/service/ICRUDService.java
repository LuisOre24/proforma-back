package com.soldev.service;

import java.util.List;

public interface ICRUDService<T>{
	
	T registrar(T entity);
	T actualizar(T entity);
	List<T> obtenerTodo();
	T obtenerUno(Integer id);
	void eliminar(Integer id);

}
