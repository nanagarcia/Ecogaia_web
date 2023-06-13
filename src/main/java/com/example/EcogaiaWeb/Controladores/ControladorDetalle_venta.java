package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.detalle_venta;
import com.example.EcogaiaWeb.Servicios.ServicioDetalle_venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorDetalle_venta {
    ServicioDetalle_venta SC;

    @Autowired
    public ControladorDetalle_venta(ServicioDetalle_venta c){
        this.SC = c;
    }

    @PostMapping(path = "/insertarCotizacion", consumes = "application/x-www-form-urlencoded" )
    public String insertar( detalle_venta c){
        return SC.insertar(c);
    }

    @GetMapping(path = "/listarCotizacion")
    public ArrayList<detalle_venta> listar(){
        return SC.listar();
    }

    @DeleteMapping("/eliminar/{codigo_cotizacion}")
    public String eliminar(@PathVariable("codigo") int cod){
        return SC.eliminar(cod);
    }

    @GetMapping("/distribuir/{correo}")
    public ResponseEntity<List<Map<String, String>>> distribuir (@PathVariable("correo") String correo) {
        List<Object[]> distribuciones = SC.distribuciones(correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] objects: distribuciones) {
            Map<String, String> datos = new HashMap<>();
            datos.put("usu_nombre", objects[0].toString());
            datos.put("usu_direccion", objects[1].toString());
            datos.put("usu_telefono", objects[2].toString());
            datos.put("id_Usuario", objects[3].toString());
            datos.put("venta_Codigo", objects[4].toString());
            datos.put("id_repartidor", objects[5].toString());
            mostrar.add(datos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }
}
