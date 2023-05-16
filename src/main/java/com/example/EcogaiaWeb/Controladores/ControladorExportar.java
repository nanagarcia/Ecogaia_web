package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Importar;
import com.example.EcogaiaWeb.Servicios.ServicioImportar;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ControladorExportar {
    ServicioImportar SE;
    public ControladorExportar(ServicioImportar se){
        this.SE = se;
    }

    @PostMapping(path = "/insertarExportar", consumes = "application/x-www-form-urlencoded")
    public String insertar(Importar e) {
        return SE.insertar(e);
    }

    @GetMapping(path = "/listarExportar")
    public ArrayList<Importar> listar() {
        return SE.listar();
    }

    @DeleteMapping("/eliminarExportar/{id}")
    public String eliminar(@PathVariable("id") int id){
        return SE.eliminar(id);
    }
}
