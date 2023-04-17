package com.soldev.repo;

import com.soldev.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClienteRepo extends JpaRepository<Cliente, Integer> {


    @Query(value = "select * from cliente where documento =:document", nativeQuery = true)
    public Cliente getCliente(@Param("document") String document);

}
