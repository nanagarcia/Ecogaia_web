package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Cotizacion;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioCotizacion;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioVenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioCotizacion {
    RepositorioCotizacion repositorio;
    RepositorioProducto repositorioProducto;
    RepositorioVenta RepositorioVenta;

    public ServicioCotizacion(RepositorioCotizacion repo){
        this.repositorio = repo;
    }

    public String insertar(Cotizacion co){
        Producto p = co.getProducto();
        Venta v = co.getVenta();
        String ms = "Alguno de los datos no existe";

        if(repositorioProducto.findById(p.getProd_Codigo()).isPresent() && repositorio.findById(v.getVenta_Codigo()).isPresent()){
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
