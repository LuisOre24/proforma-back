package com.soldev.service.impl;

import com.soldev.entity.Atencion;
import com.soldev.repo.IAtencionRepo;
import com.soldev.repo.IClienteRepo;
import com.soldev.service.IAtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionServiceImpl implements IAtencionService {

    @Autowired
    private IAtencionRepo repo;

    private IClienteRepo repoCliente;

    @Override
    public Atencion registrar(Atencion entity) {

        entity.getDetallePago().forEach( det ->{
            det.setAtencion(entity);
        });

        return repo.save(entity);
    }

    @Override
    public Atencion actualizar(Atencion entity) {

        entity.getDetallePago().forEach(det ->{
            det.setAtencion(entity);
        });
        return repo.save(entity);
    }

    @Override
    public List<Atencion> obtenerTodo() {
        return repo.findAll();
    }

    @Override
    public Atencion obtenerUno(Integer id) {
        Optional<Atencion> op = repo.findById(id);
        return op.isPresent() ? op.get() : new Atencion();
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    public Page<Atencion> listPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
