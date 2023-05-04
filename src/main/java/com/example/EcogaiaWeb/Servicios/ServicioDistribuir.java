package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Distribuir;
import com.example.EcogaiaWeb.Repositorios.RepositorioDistribuir;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioDistribuir {

    RepositorioDistribuir repositorio;

    public ServicioDistribuir(RepositorioDistribuir repository){
        this.repositorio = repository;
    }

    public String insertar(Distribuir d){
        repositorio.save(d);
        return "El envio se agrego";
    }

    public ArrayList<Distribuir> listar(){
        return (ArrayList<Distribuir>) repositorio.findAll();
    }

    public String eliminar(Integer codigo){
        String ms = "No se elimino el envio";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "El envio se elimino correctamente";
        }
        return ms;
    }
}
