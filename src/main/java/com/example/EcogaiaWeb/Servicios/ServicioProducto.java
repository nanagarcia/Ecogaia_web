package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioProducto {
    RepositorioProducto repositorio;

    public ServicioProducto(RepositorioProducto repository){
        this.repositorio = repository;
    }

    public String insertar (Producto p) {
        repositorio.save(p);
        return "El producto se agrego";
    }

    public ArrayList<Producto> listar () {
        return (ArrayList<Producto>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino el producto";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "El producto se elimino correctamente";
        }
        return ms;
    }

}
