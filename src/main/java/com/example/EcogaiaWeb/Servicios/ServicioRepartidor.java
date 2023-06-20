package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioRepartidor;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioRepartidor {
    RepositorioRepartidor repositorio;
    RepositorioUsuario repositorioUsuario;
    RepositorioRepartidor repositorioRepartidor;
    RepositorioProducto repositorioProducto;

    public ServicioRepartidor(RepositorioRepartidor repo, RepositorioUsuario usuario, RepositorioRepartidor repositorioRepartidor, RepositorioProducto repositorioProducto){
        this.repositorio = repo;
        this.repositorioUsuario = usuario;
        this.repositorioRepartidor = repositorioRepartidor;
        this.repositorioProducto =  repositorioProducto;
    }

    public String insertar(Repartidor rep){
        repositorio.save(rep);
        return "El Repartidor se agrego";
    }

    public ArrayList<Repartidor> listar(){
        return (ArrayList<Repartidor>) repositorio.findAll();
    }

    public String eliminar(Integer id_rep ){
        String ms = "No se elimino al repartidor";
        if (repositorio.existsById(id_rep)){
            repositorio.deleteById(id_rep);
            ms = "El repartidor se elimino exitosamente";
        }
        return ms;
    }



}
