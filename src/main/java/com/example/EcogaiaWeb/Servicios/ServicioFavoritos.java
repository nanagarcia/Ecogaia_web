package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioFavoritos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioFavoritos {
    RepositorioFavoritos repositorio;

    public ServicioFavoritos(RepositorioFavoritos repo){
        this.repositorio = repo;
    }

    public String insertar(Favoritos F){
        repositorio.save(F);
        return "Se agrego a favoritos";
    }

    public ArrayList<Favoritos> listar(){
        return (ArrayList<Favoritos>) repositorio.findAll();
    }

    public String eliminar(Integer codigo_favoritos){
        String ms = "No se elimino de favoritos";
        if (repositorio.existsById(codigo_favoritos)){
            repositorio.deleteById(codigo_favoritos);
            ms = "Se elimino de favoritos";
        }
        return ms;
    }
}
