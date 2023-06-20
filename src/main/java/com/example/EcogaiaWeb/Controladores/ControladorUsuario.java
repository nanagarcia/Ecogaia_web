package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Servicios.ServicioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorUsuario {

    ServicioUsuario SU;

    public ControladorUsuario(ServicioUsuario s) {
        this.SU = s;
    }

    @PostMapping(path = "/insertarUsuario", consumes = "application/x-www-form-urlencoded")
    public boolean insertar(Usuario u) {
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
    public Map<String, String> login(@PathVariable("email") String email, @PathVariable("pass") String password){
        return SU.login(email,password);
    }
    @PutMapping(path = "/actualizarUsuario/{correo}")
    public String actualizar(@PathVariable("correo") String correo, Usuario u) {
        return SU.actualizar(u, correo);
    }

    @GetMapping(path = "/usuario/{correo}")
    public ResponseEntity<Map<String, String>> perfil(@PathVariable("correo") String correo) {
        List<Object[]> object = SU.perfil(correo);
        Map<String, String> mostrar = new HashMap<String, String>();
        object.forEach((o) -> {
            mostrar.put("id_usuario", o[0].toString());
            mostrar.put("usu_nombre", o[1].toString());
            mostrar.put("usu_correo", o[2].toString());
            mostrar.put("usu_direccion", o[3].toString());
            mostrar.put("usu_contrasenia", o[4].toString());
            mostrar.put("usu_telefono", o[5].toString());
        });
        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }

    @PutMapping("/cambiarRol/{correo}/{rol}")
    public String rol (@PathVariable("correo") String correo, @PathVariable("rol") String rol) {
        return SU.rol(correo, rol);
    }
}
