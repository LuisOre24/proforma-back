package com.soldev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soldev.entity.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer>{

	@Query(value = "select producto from producto where producto ilike %:keyword%", nativeQuery = true)
	public List<String> search(@Param("keyword") String keyword);
	
}
