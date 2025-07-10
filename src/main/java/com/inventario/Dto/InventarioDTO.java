package com.inventario.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
    private Integer idInventario;
    private Integer idProducto;
    private Integer stockDisponible;
    private String ubicacionBodega;
    private String link;
}
