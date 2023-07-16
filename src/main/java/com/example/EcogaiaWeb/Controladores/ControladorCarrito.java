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
            datos.put("prod_precio", objects[1].toString());
            datos.put("cantidad", objects[2].toString());
            datos.put("total", objects[3].toString());
            datos.put("Prod_Nombre", objects[4].toString());
            datos.put("id_Usuario", objects[5].toString());
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

    @GetMapping("/productosCarrito/{correo}")
    public ResponseEntity<List<Map<String, String>>> carritoUsuario(@PathVariable("correo") String correo) {
        List<Object[]> cotizaciones = SC.carritoUsuario(correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        cotizaciones.forEach((element) -> {
            Map<String, String> datos = new HashMap<>();
            datos.put("codigo_carrito", element[0].toString());
            datos.put("cantidad", element[1].toString());
            datos.put("total", element[2].toString());
            datos.put("codigo_prod", element[3].toString());
            datos.put("id_Usuario", element[4].toString());
            datos.put("Prod_Nombre", element[5].toString());
            mostrar.add(datos);
        });

        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }


    @GetMapping(path = "/nombreProdCar/{correo}/{nombre}")
    public ResponseEntity<List<Map<String, String>>>  filtrar (@PathVariable("nombre") String nombre, @PathVariable("correo") String correo) {
        List<Object[]>buscar = SC.filtrar(nombre,correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        buscar.forEach((element) -> {
            Map<String, String> datos = new HashMap<>();
            datos.put("codigo_carrito", element[0].toString());
            datos.put("cantidad", element[1].toString());
            datos.put("total", element[2].toString());
            datos.put("codigo_prod", element[3].toString());
            datos.put("id_Usuario", element[4].toString());
            datos.put("Prod_Nombre", element[5].toString());
            mostrar.add(datos);
        });

        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }

    @GetMapping("/ordenarCarNombre/{correo}")
    public ResponseEntity<List<Map<String, String>>> ordenarCarNombre (@PathVariable("correo") String correo) {
        return ResponseEntity.status(HttpStatus.OK).body(SC.ordenarCarNombre(correo));
    }
    @GetMapping("/ordenarCarCantidad/{correo}")
    public ResponseEntity<List<Map<String, String>>> ordenarCarCantidad (@PathVariable("correo") String correo) {
        return ResponseEntity.status(HttpStatus.OK).body(SC.ordenarCarCantidad(correo));
    }
    @GetMapping("/ordenarCarTotal/{correo}")
    public ResponseEntity<List<Map<String, String>>> ordenarCarTotal (@PathVariable("correo") String correo) {
        return ResponseEntity.status(HttpStatus.OK).body(SC.ordenarCarTotal(correo));
    }


}
