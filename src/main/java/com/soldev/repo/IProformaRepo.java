package com.soldev.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.soldev.entity.Proforma;

public interface IProformaRepo extends JpaRepository<Proforma, Integer>{

	Page<Proforma> findAllByOrderByIdProformaDesc(Pageable pageable);

	
}
