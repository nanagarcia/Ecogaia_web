package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public boolean login (String email, String password) {
        ArrayList<Usuario> usuarios = this.listar();
        boolean res = false;
        for (Usuario u: usuarios){
            if (u.getUsu_correo().equals(email) && u.getUsu_contrasenia().equals(password)){
                res = true;
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
}
