package com.upiiz.ventas.controller;

import com.upiiz.ventas.model.Venta;
import com.upiiz.ventas.service.VentasServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentasControllers {

    // Requiero inyectar el servicio
    VentasServices ventasService;
    public VentasControllers(VentasServices ventasService){
        this.ventasService = ventasService;
    }

    // Get - Todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> getVentas(){
        return ResponseEntity.ok(ventasService.getAllVentas());
    }

    // Get - Venta por id
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVenta(@PathVariable Long id){
        Optional<Venta> venta = ventasService.getVentaById(id);
        if (venta.isPresent()) {
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Post - Crear venta
    @PostMapping
    public ResponseEntity<Venta> addVenta(@RequestBody Venta venta){
        return ResponseEntity.ok(ventasService.createVenta(venta));
    }

    // Put - Actualizar venta

    @PutMapping("/{id}")
    public ResponseEntity<Venta> putVenta(@RequestBody Venta venta, @PathVariable Long id){
        // Que se requiere antes de actualizarla??
        venta.setId(id);
        return ResponseEntity.ok(ventasService.updateVenta(id, venta));
    }

    // Delete - Eliminar venta por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id){
        ventasService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }

}