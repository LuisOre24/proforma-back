package com.soldev.service.impl;

import com.soldev.entity.Cliente;
import com.soldev.repo.IClienteRepo;
import com.soldev.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepo repo;

    @Override
    public Cliente registrar(Cliente entity) {
        return repo.save(entity);
    }

    @Override
    public Cliente actualizar(Cliente entity) {
        return repo.save(entity);
    }

    @Override
    public List<Cliente> obtenerTodo() {
        return repo.findAll();
    }

    @Override
    public Cliente obtenerUno(Integer id) {

        Optional<Cliente> op = repo.findById(id);
        return op.isPresent()? op.get() : new Cliente();
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    public Cliente getClienteDocumento(String documento){
        return repo.getCliente(documento);
    }

    public Page<Cliente> listPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
