package com.example.EcogaiaWeb.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EcogaiaWeb.Servicios.ServicioCarrito;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorCarrito {
    ServicioCarrito SC;
    public ControladorCarrito (ServicioCarrito s){
        this.SC = s;
    }

    @PostMapping("/insertarCarrito/{correo}/{codigo}/{cantidad}")
    public boolean insertar (@PathVariable("correo") String correo, @PathVariable("codigo") Integer codigo, @PathVariable("cantidad") int cantidad) {
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

    @DeleteMapping("/eliminarCarrito/{codigo}")
    public String eliminar (@PathVariable("codigo") Integer codigo) {
        return SC.eliminar(codigo);
    }


    @GetMapping("/compra/{correo}")
    public String compras (@PathVariable("correo") String correo) {
        return SC.compra(correo);
    }

    @PostMapping("/sumarCarrito/{codigo}")
    public boolean sumar ( @PathVariable("codigo") Integer codigo) {
        return SC.sumar(codigo);
    }

    @PostMapping("/restarCarrito/{codigo}")
    public boolean restar ( @PathVariable("codigo") Integer codigo) {
        return SC.restar(codigo);
    }

}
