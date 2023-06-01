package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ServicioUsuario {

    RepositorioUsuario repositorio;

    public ServicioUsuario(RepositorioUsuario repository){
        this.repositorio = repository;
    }

    public String insertar(Usuario u){
        repositorio.save(u);
        return "El usuario se agrego";
    }

    public String login (String email, String password) {
        ArrayList<Usuario> usuarios = this.listar();
        String res = "Usuario o contraseña incorrectos";
        for (Usuario u: usuarios){
            if (u.getUsu_correo().equals(email) && u.getUsu_contrasenia().equals(password)){
                res = u.getId_Usuario().toString();
            }
        }
        return res;
    }

    public ArrayList<Usuario> listar(){
        return (ArrayList<Usuario>) repositorio.findAll();
    }

    public String eliminar(Integer id){
        String ms = "No se elimino el usuario";
        if (repositorio.existsById(id)){
            repositorio.deleteById(id);
            ms = "El usuario se elimino correctamente";
        }
        return ms;
    }

    public String actualizar (Usuario u) {
        String msg = "No se actualizo";
        if (repositorio.actualizar(u.getUsu_nombre(), u.getUsu_telefono(), u.getUsu_direccion(), u.getUsu_correo(), u.getUsu_contrasenia(), u.getId_Usuario()) > 0) {
            msg = "Actualizado correctamente";
        }
        return msg;
    }

    public Optional<Usuario> usuarioId (Integer id) {
        return repositorio.findById(id);
    }
}
