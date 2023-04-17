package com.soldev.repo;

import com.soldev.entity.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAtencionRepo extends JpaRepository<Atencion, Integer> {
}
