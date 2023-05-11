package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Cotizacion;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioCotizacion;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioCotizacion {
    RepositorioCotizacion repositorio;
    RepositorioUsuario repositorioUsuario;
    RepositorioProducto repositorioProducto;

    public ServicioCotizacion(RepositorioCotizacion repo){
        this.repositorio = repo;
    }

    public String insertar(Cotizacion co){
        Usuario u = co.getUsuario();
        Producto p = co.getProducto();
        String ms = "Alguno de los datos no existe";

        if(repositorioUsuario.findById(u.getId_Usuario()).isPresent() && repositorioProducto.findById(p.getProd_Codigo()).isPresent()){
            repositorio.save(co);
            ms = "El favorito se agrego exitosamente";
        }
        return ms;
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
