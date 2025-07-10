package com.inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.Model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{

}
