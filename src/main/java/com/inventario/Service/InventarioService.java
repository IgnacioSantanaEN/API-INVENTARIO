package com.inventario.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.Dto.InventarioDTO;
import com.inventario.Model.Inventario;
import com.inventario.Repository.InventarioRepository;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository repository;

    public List<InventarioDTO> GetAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Inventario findByid(Integer idProducto){
        return repository.findById(idProducto).orElse(null);
    }

    public Inventario UpdateById(Integer idProducto, Inventario actualizado){
        Inventario existente = repository.findById(idProducto).orElse(null);
        
        existente.setStockDisponible(actualizado.getStockDisponible());
        existente.setUbicacionBodega(actualizado.getUbicacionBodega());

        return repository.save(existente);
    }

    public Inventario save(Inventario inventario){
        return repository.save(inventario);
    }

    public InventarioDTO toDto(Inventario inventario){
        InventarioDTO dto = new InventarioDTO();
        dto.setIdInventario(inventario.getIdInventario());
        dto.setIdProducto(inventario.getIdProducto());
        dto.setStockDisponible(inventario.getStockDisponible());
        dto.setUbicacionBodega(inventario.getUbicacionBodega());

        dto.setLink("http://localhost:8087/api/productos/"+ inventario.getIdProducto());

        return dto;
    }
}
