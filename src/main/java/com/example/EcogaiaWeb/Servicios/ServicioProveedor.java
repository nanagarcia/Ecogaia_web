package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Proveedor;
import com.example.EcogaiaWeb.Repositorios.RepositorioProveedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ServicioProveedor {
    RepositorioProveedor repositorio;

    public ServicioProveedor(RepositorioProveedor repository) {
        this.repositorio = repository;
    }

    public String insertar (Proveedor p) {
        repositorio.save(p);
        return "El proveedor se agrego";
    }

    public ArrayList<Proveedor> listar () {
        return  (ArrayList<Proveedor>) repositorio.findAll();
    }

    public String eliminar(Integer RUC) {
        String ms = "No se elimino el proveedor";
        if (repositorio.existsById(RUC)) {
            repositorio.deleteById(RUC);
            ms = "El proveedor se elimino correctamente";
        }
        return ms;
    }
}
