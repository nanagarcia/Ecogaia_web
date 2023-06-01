package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Orden_pedido;
import com.example.EcogaiaWeb.Servicios.ServicioOrden_pedido;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorOrden_pedido {

    ServicioOrden_pedido SOP;
    public ControladorOrden_pedido(ServicioOrden_pedido s){
        this.SOP = s;
    }

    @PostMapping(path = "/insertarOrdenpedido", consumes = "application/x-www-form-urlencoded")
    public String insertar(Orden_pedido op) {
        return SOP.insertar(op);
    }

    @GetMapping(path = "/listarOrdenPedido")
    public ArrayList<Orden_pedido> listar() {
        return SOP.listar();
    }

    @DeleteMapping("/eliminarOrdenPedido/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SOP.eliminar(codigo);
    }
}
