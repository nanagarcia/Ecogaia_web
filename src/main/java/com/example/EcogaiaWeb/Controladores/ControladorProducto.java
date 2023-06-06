package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Servicios.ServicioProducto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorProducto {
    ServicioProducto SP;
    public ControladorProducto(ServicioProducto s){
        this.SP = s;
    }

    @PostMapping(path = "/insertarProducto", consumes = "application/x-www-form-urlencoded")
    public String insertar(Producto p) {
        return SP.insertar(p);
    }

    @GetMapping(path = "/listarProducto")
    public ArrayList<Producto> listar() {
        return SP.listar();
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SP.eliminar(codigo);
    }

    @GetMapping("/categoriasProducto/{cat}")
    public ArrayList<Producto> categorias(@PathVariable("cat") String cat) {
        return SP.categoria(cat);
    }
}
