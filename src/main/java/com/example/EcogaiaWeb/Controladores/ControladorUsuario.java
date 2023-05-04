package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Servicios.ServicioUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorUsuario {

    ServicioUsuario SU;

    public ControladorUsuario(ServicioUsuario s){
        this.SU = s;
    }

    @PostMapping(path = "/insertarUsuario", consumes = "application/x-www-form-urlencoded")
    public String insertar(Usuario u) {
        return SU.insertar(u);
    }

    @GetMapping(path = "/listarUsuario")
    public ArrayList<Usuario> listar(){
        return SU.listar();
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public String eliminar(@PathVariable("id") int id){
        return SU.eliminar(id);
    }
}
