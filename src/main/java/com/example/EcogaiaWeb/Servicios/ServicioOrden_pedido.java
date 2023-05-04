package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Orden_pedido;
import com.example.EcogaiaWeb.Repositorios.RepositorioOrden_pedido;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class ServicioOrden_pedido {

    RepositorioOrden_pedido repositorio;

    public ServicioOrden_pedido( RepositorioOrden_pedido repository){this.repositorio = repository;}

    public String insertar (Orden_pedido op) {
        repositorio.save(op);
        return "La orden de pedido se agrego";
    }

    public ArrayList<Orden_pedido> listar () {
        return (ArrayList<Orden_pedido>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino la orden de pedido";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "La orden de pedido se elimino correctamente";
        }
        return ms;
    }
}
