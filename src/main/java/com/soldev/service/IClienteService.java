package com.soldev.service;

import com.soldev.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService extends ICRUDService<Cliente>{

    public Page<Cliente> listPageable(Pageable pageable);
    public Cliente getClienteDocumento(String document);

}
