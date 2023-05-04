package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioVenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ServicioVenta {
    RepositorioVenta repositorio;

    public ServicioVenta(RepositorioVenta repository) {
        this.repositorio = repository;
    }

    public String insertar (Venta v) {
        repositorio.save(v);
        return "La venta se agrego";
    }

    public ArrayList<Venta> listar () {
        return (ArrayList<Venta>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino la venta";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "La venta se elimino correctamente";
        }
        return ms;
    }
}
