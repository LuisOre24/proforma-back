package com.soldev.service;

import com.soldev.entity.Atencion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAtencionService extends ICRUDService<Atencion>{

    public Page<Atencion> listPageable(Pageable pageable);

}
