package com.soldev.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soldev.entity.Proforma;

public interface IProformaService extends ICRUDService<Proforma>{

	public Page<Proforma> listPageable(Pageable pageable);
	
	byte[] generarProforma(Proforma proforma);
	
}
