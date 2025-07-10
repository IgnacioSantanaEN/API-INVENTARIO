package com.inventario.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.Dto.InventarioDTO;
import com.inventario.Mapper.InventarioMapper;
import com.inventario.Model.Inventario;
import com.inventario.Repository.InventarioRepository;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioDTO> GetAll() {
        return inventarioRepository.findAll().stream()
            .map(InventarioMapper::toDto)
            .collect(Collectors.toList());

    }

    public Inventario findByid(Integer idProducto){
        return inventarioRepository.findById(idProducto).orElse(null);
    }

    public Inventario UpdateById(Integer idProducto, Inventario actualizado){
        Inventario existente = inventarioRepository.findById(idProducto).orElse(null);
        
        existente.setStockDisponible(actualizado.getStockDisponible());
        existente.setUbicacionBodega(actualizado.getUbicacionBodega());

        return inventarioRepository.save(existente);
    }

    public Inventario save(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

}
