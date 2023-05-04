package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import com.example.EcogaiaWeb.Repositorios.RepositorioProd_tips;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioProd_tips {

    RepositorioProd_tips repositorio;

    public ServicioProd_tips(RepositorioProd_tips repository){
        this.repositorio = repository;
    }

    public String insertar(Prod_tips prod){
        repositorio.save(prod);
        return "El tip se agrego";
    }

    public ArrayList<Prod_tips> listar(){
        return (ArrayList<Prod_tips>) repositorio.findAll();
    }

    public String eliminar(Integer codigo){
        String ms = "No se elimino el tip";
        if(repositorio.existsById(codigo)){
            repositorio.deleteById(codigo);
            ms = "El tip se elimino correctamente";
        }
        return ms;
    }
}
