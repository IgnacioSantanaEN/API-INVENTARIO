package com.inventario.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.inventario.Dto.InventarioDTO;
import com.inventario.Model.Inventario;
import com.inventario.Service.InventarioService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/")
    public ResponseEntity<?> EnlistarProductos() {
        List<InventarioDTO> productos= inventarioService.GetAll();

       if(productos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("No existen productos que mostrar"));
       }

       return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ConsultarProducto(@PathVariable Integer id){
        Inventario inventario = inventarioService.findByid(id);

        if(inventario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Producto no encontrado"));
        }

        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    public ResponseEntity<?> RegistrarInventario(@RequestBody Inventario inventario){
        Inventario nuevo = inventarioService.save(inventario);

        if(nuevo == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Mensaje("Producto no pudo ser registrado"));
        }
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new Mensaje("Producto registrado: "+ nuevo.getIdInventario()));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> ModificarProducto(@PathVariable Integer id, @RequestBody Inventario existente){
        Inventario modificado = inventarioService.UpdateById(id, existente);

        if(modificado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Producto no encontrado"));
        }

        return ResponseEntity.ok(modificado);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mensaje{
        private String mensaje;
    }
}
