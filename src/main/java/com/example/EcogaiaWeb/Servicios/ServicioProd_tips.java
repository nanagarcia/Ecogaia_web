package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import com.example.EcogaiaWeb.Repositorios.RepositorioProd_tips;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ServicioProd_tips {

    RepositorioProd_tips repositorio;
    RepositorioUsuario RepositorioUsuario;
    ArrayList<Prod_tips> mostrar = new ArrayList<Prod_tips>();

    public ServicioProd_tips(RepositorioProd_tips repository){
        this.repositorio = repository;
    }

    public String insertar(String correo, Prod_tips prod){
        String ms = "No se agrego el tip";
        if (repositorio.insert(prod.getCuerpo(), prod.getFecha(), prod.getTitulo(), correo) == 1) {
            ms = "El tip se agrego correctamente";
        };
        return ms;
    }

    public ArrayList<Prod_tips> listar(){
        return (ArrayList<Prod_tips>) repositorio.findAll();
    }

    public String eliminar(Integer codigo){
        String ms = "No se pudo eliminar el tip";
        if(repositorio.existsById(codigo)){
            repositorio.deleteById(codigo);
            ms = "El tip se elimino correctamente";
        }
        return ms;
    }

    public ArrayList<Prod_tips> titulo (String titulo) {
        ArrayList<Prod_tips> tips = (ArrayList<Prod_tips>) repositorio.findAll();
        mostrar.clear();
        for (Prod_tips i: tips){
            if (i.getTitulo().toLowerCase(Locale.ROOT).startsWith(titulo.toLowerCase(Locale.ROOT))){
                mostrar.add(i);
            }
        }
        return mostrar;
    }
}
