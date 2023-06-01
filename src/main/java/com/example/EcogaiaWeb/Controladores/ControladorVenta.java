package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Servicios.ServicioVenta;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorVenta {
    ServicioVenta SV;

    public ControladorVenta(ServicioVenta s) {
        this.SV = s;
    }

    @PostMapping(path = "/insertarVenta", consumes = "application/x-www-form-urlencoded")
    public String insertar(Venta v) {
        return SV.insertar(v);
    }

    @GetMapping(path = "/listarVenta")
    public ArrayList<Venta> listar() {
        return SV.listar();
    }

    @DeleteMapping("/eliminarVenta/{codigo}")
    public String eliminar(@PathVariable("codigo") int codigo) {
        return SV.eliminar(codigo);
    }
}
