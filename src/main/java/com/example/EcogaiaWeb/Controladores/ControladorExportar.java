package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Exportar;
import com.example.EcogaiaWeb.Servicios.ServicioExportar;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ControladorExportar {
    ServicioExportar SE;
    public ControladorExportar(ServicioExportar se){
        this.SE = se;
    }

    @PostMapping(path = "/insertarExportar", consumes = "application/x-www-form-urlencoded")
    public String insertar(@RequestBody Exportar e) {
        return SE.insertar(e);
    }

    @GetMapping(path = "/listarExportar")
    public ArrayList<Exportar> listar() {
        return SE.listar();
    }

    @DeleteMapping("/eliminarExportar/{id}")
    public String eliminar(@PathVariable("id") int id){
        return SE.eliminar(id);
    }
}
