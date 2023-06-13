package com.example.EcogaiaWeb.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcogaiaWeb.Entidades.Carrito;
import com.example.EcogaiaWeb.Servicios.ServicioCarrito;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorCarrito {
    ServicioCarrito SC;
    public ControladorCarrito (ServicioCarrito s){
        this.SC = s;
    }

    @PostMapping("/insertarCarrito/{correo}/{codigo}/{cantidad}")
    public String insertar (@PathVariable("correo") String correo, @PathVariable("codigo") Integer codigo, @PathVariable("cantidad") int cantidad) {
        return SC.insertar(correo,codigo,cantidad);
    }

    @GetMapping("/cotizacionesUsuario/{correo}")
    public ResponseEntity<List<Map<String, String>>> cotizacionUsuario(@PathVariable("correo") String correo) {
        List<Object[]> detalles_venta = SC.cotizacionesUsuario(correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        for(Object[] objects: detalles_venta) {
            Map<String, String> datos = new HashMap<>();
            datos.put("codigo_carrito", objects[0].toString());
            datos.put("cantidad", objects[1].toString());
            datos.put("total", objects[2].toString());
            datos.put("Prod_Nombre", objects[3].toString());
            datos.put("id_Usuario", objects[4].toString());
            mostrar.add(datos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }

}
