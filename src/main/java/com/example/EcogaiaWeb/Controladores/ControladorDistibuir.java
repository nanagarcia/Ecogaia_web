package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Distribuir;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Servicios.ServicioDistribuir;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorDistibuir {

    ServicioDistribuir SD;

    public ControladorDistibuir(ServicioDistribuir s){
        this.SD = s;
    }

    @PostMapping(path = "/insertarEnvio", consumes = "application/x-www-form-urlencoded")
    public String insertar(Distribuir d) {
        return SD.insertar(d);
    }

    @GetMapping(path = "/listarEnvio")
    public ArrayList<Distribuir> listar() {
        return SD.listar();
    }

    @DeleteMapping("/eliminarEnvio/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SD.eliminar(codigo);
    }
}
