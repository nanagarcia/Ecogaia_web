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
    public String insertar(Producto p) {
        return SP.insertar(p);
    }


<<<<<<< HEAD
    @GetMapping(path = "/listarProductos")
=======
    @GetMapping(path = "/listarProducto")
>>>>>>> 117a789b467777c9d6fdc5edad5dfc943552c0e9
    public ArrayList<Producto> listar() {
        return SP.listar();
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SP.eliminar(codigo);
    }
}
