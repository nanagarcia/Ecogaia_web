package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Proveedor;
import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Servicios.ServicioProveedor;
import com.example.EcogaiaWeb.Servicios.ServicioVenta;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorProveeedor {
    ServicioProveedor SP;

    public ControladorProveeedor(ServicioProveedor s) {
        this.SP = s;
    }

    @PostMapping(path = "/insertarProveedor", consumes = "application/x-www-form-urlencoded")
    public String insertar(Proveedor p) {
        return SP.insertar(p);
    }

    @GetMapping(path = "/listarProveedor")
    public ArrayList<Proveedor> listar() {
        return SP.listar();
    }

    @DeleteMapping("/eliminarProveedor/{RUC}")
    public String eliminar(@PathVariable("RUC") Integer RUC) {
        return SP.eliminar(RUC);
    }
}
