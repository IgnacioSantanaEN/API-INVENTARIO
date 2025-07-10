package com.inventario.Mapper;


import com.inventario.Dto.InventarioDTO;
import com.inventario.Model.Inventario;

public class InventarioMapper {
    
    public static InventarioDTO toDto(Inventario inventario){
        InventarioDTO dto = new InventarioDTO();
        dto.setIdInventario(inventario.getIdInventario());
        dto.setIdProducto(inventario.getIdProducto());
        dto.setStockDisponible(inventario.getStockDisponible());
        dto.setUbicacionBodega(inventario.getUbicacionBodega());

        dto.setLink("http://localhost:8087/api/productos/"+ inventario.getIdProducto());

        return dto;
    }
}
