package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Cotizacion;
import com.example.EcogaiaWeb.Servicios.ServicioCotizacion;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorCotizacion {
    ServicioCotizacion SC;

    public ControladorCotizacion(ServicioCotizacion c){
        this.SC = c;
    }

    @PostMapping(path = "/insertarCotizacion", consumes = "application/x-www-form-urlencoded" )
    public String insertar( Cotizacion c){
        return SC.insertar(c);
    }

    @GetMapping(path = "/listarCotizacion")
    public ArrayList<Cotizacion> listar(){
        return SC.listar();
    }

    @DeleteMapping("/eliminar/{codigo_cotizacion}")
    public String eliminar(@PathVariable("codigo") int cod){
        return SC.eliminar(cod);
    }
}
