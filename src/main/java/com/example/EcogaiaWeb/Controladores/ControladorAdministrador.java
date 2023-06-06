package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Administrador;
import com.example.EcogaiaWeb.Servicios.ServicioAdministrador;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorAdministrador {
    ServicioAdministrador SA;
    public ControladorAdministrador(ServicioAdministrador sa){
        this.SA = sa;
    }

    @PostMapping(path = "/insertarAdministrador", consumes = "application/x-www-form-urlencoded")
    public String insertar(Administrador a) {
        return SA.insertar(a);
    }

    @GetMapping(path = "/listarAdministrador")
    public ArrayList<Administrador> listar() {
        return SA.listar();
    }

    @DeleteMapping("/eliminarAdministrador/{id}")
    public String eliminar(@PathVariable("id") int id){
        return SA.eliminar(id);
    }
}
