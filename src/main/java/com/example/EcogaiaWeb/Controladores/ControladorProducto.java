package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Servicios.ServicioProducto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorProducto {
    ServicioProducto SP;
    public ControladorProducto(ServicioProducto s){
        this.SP = s;
    }

    @PostMapping(path = "/insertarProducto", consumes = "application/x-www-form-urlencoded")
    public String insertar(@RequestBody Producto p) {
        return SP.insertar(p);
    }

<<<<<<< HEAD
    @GetMapping(path = "/listarProductos")
=======
    @GetMapping(path = "/listarProducto")
>>>>>>> c5ac092c1c24dafc629a85315eb45c4dff14580d
    public ArrayList<Producto> listar() {
        return SP.listar();
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SP.eliminar(codigo);
    }
}
