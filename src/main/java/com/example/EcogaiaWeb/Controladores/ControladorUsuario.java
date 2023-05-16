package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Servicios.ServicioUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorUsuario {

    ServicioUsuario SU;

    public ControladorUsuario(ServicioUsuario s) {
        this.SU = s;
    }

    @PostMapping(path = "/insertarUsuario", consumes = "application/x-www-form-urlencoded")
    public String insertar(Usuario u) {
        System.out.println(u);
        return SU.insertar(u);
    }

    @GetMapping(path = "/listarUsuario")
    public ArrayList<Usuario> listar() {
        return SU.listar();
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public String eliminar(@PathVariable("id") int id) {
        return SU.eliminar(id);
    }

    @GetMapping(path = "/validarUsuario/{email}/{pass}")
    public boolean login(@PathVariable("email") String email, @PathVariable("pass") String password){
        return SU.login(email,password);
    }
}
