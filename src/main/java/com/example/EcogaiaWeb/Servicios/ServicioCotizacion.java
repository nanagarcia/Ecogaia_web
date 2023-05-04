package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Cotizacion;
import com.example.EcogaiaWeb.Repositorios.RepositorioCotizacion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioCotizacion {
    RepositorioCotizacion repositorio;

    public ServicioCotizacion(RepositorioCotizacion repo){
        this.repositorio = repo;
    }

    public String insertar(Cotizacion co){
        repositorio.save(co);
        return "Se agrego la cotizacion";
    }

    public ArrayList<Cotizacion> listar(){
        return (ArrayList<Cotizacion>) repositorio.findAll();
    }

    public String eliminar (Integer codigo_cotizacion){
        String ms = "No se elimino la cotizacion";
        if (repositorio.existsById(codigo_cotizacion)){
            repositorio.deleteById(codigo_cotizacion);
            ms = "La cotizacion se elimino exitosamente";
        }
        return ms;
    }
}
