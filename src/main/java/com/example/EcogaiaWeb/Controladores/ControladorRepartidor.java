package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Servicios.ServicioRepartidor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorRepartidor {
    ServicioRepartidor SR;

    public ControladorRepartidor(ServicioRepartidor repartidor){
        this.SR = repartidor;
    }

    @PostMapping(path = "/insertarrepartidor", consumes = "application/x-www-form-urlencoded")
    public String insertar( Repartidor R){
        return SR.insertar(R);
    }

    @GetMapping(path = "/listarRepartidor")
    public ArrayList<Repartidor> listar(){
        return SR.listar();
    }

    @DeleteMapping("/eliminarRepartidor/{id}")
    public String eliminar(@PathVariable("id")int id){
        return SR.eliminar(id);
    }
}
