package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Repositorios.RepositorioRepartidor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioRepartidor {
    RepositorioRepartidor repositorio;

    public ServicioRepartidor(RepositorioRepartidor repo){
        this.repositorio = repo;
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

    public List<Object[]> distribuciones(Integer id_rep) {
        return repositorio.distribuir(id_rep);
    }

}
