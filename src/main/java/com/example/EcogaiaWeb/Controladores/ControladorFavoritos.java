package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Servicios.ServicioFavoritos;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class ControladorFavoritos {
    ServicioFavoritos SF;

    public ControladorFavoritos(ServicioFavoritos serF){
        this.SF = serF;
    }

    @PostMapping(path = "/insertarFavoritos", consumes = "application/x-www-form-urlencoded")
    public String insertar(@RequestBody Favoritos f){
        return SF.insertar(f);
    }

    @GetMapping(path = "/listarFavoritos")
    public ArrayList<Favoritos>listar(){
        return SF.listar();
    }

    @DeleteMapping("/eliminarFavoritos/{codigo}")
    public String eliminar(@PathVariable("codigo")int cod){
        return SF.eliminar(cod);
    }
}
