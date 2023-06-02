package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.*;
import com.example.EcogaiaWeb.Repositorios.RepositorioAdministrador;
import com.example.EcogaiaWeb.Repositorios.RepositorioOrden_pedido;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioProveedor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class ServicioOrden_pedido {

    RepositorioOrden_pedido repositorio;
    RepositorioAdministrador RepositorioAdministrador;
    RepositorioProducto RepositorioProducto;
    RepositorioProveedor RepositorioProveedor;

    public ServicioOrden_pedido( RepositorioOrden_pedido repository){this.repositorio = repository;}

    public String insertar (Orden_pedido op) {
        Administrador a = op.getAdministrador();
        Producto p = op.getProducto();
        Proveedor pr = op.getProveedor();
        String ms = "Alguno de los datos no existe";
        if (RepositorioAdministrador.findById(a.getId_admin()).isPresent() && RepositorioProducto.findById(p.getProd_Codigo()).isPresent() && RepositorioProveedor.findById(pr.getRUC()).isPresent()) {
            repositorio.save(op);
            ms = "La orden de pedido se agrego";
        }
        return ms;
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
