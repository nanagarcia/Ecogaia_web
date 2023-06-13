package com.example.EcogaiaWeb.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.EcogaiaWeb.Entidades.Carrito;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioCarrito;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioRepartidor;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;

@Service
public class ServicioCarrito {
    RepositorioCarrito repositorio;
    RepositorioProducto repositorioProducto;
    RepositorioUsuario repositorioUsuario;
    RepositorioRepartidor repositorioRepartidor;

    public ServicioCarrito(RepositorioCarrito repositorioCarrito, RepositorioProducto repositorioProducto,
            RepositorioUsuario repositorioUsuario, RepositorioRepartidor repositorioRepartidor) {
        this.repositorio = repositorioCarrito;
        this.repositorioProducto = repositorioProducto;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public ArrayList<Carrito> listar() {
        return (ArrayList<Carrito>) repositorio.findAll();
    }

    public List<Object[]> cotizacionesUsuario(String correo) {
        return repositorio.cotizaciones(correo);
    }

    public String insertar(String correo, Integer codigo, int cantidad) {
        ArrayList<Carrito> cotizaciones = (ArrayList<Carrito>) repositorio.findAll();
        Object[] object = repositorioUsuario.usuario(correo).get(0);

        Producto product = repositorioProducto.findById(codigo).get();
        Usuario user = repositorioUsuario.findById(Integer.parseInt(object[0].toString())).get();

        String ms = "";
        Boolean isIn = false;
        for (Carrito ct : cotizaciones) {
            if (product.getProd_Codigo() == ct.getProducto().getProd_Codigo()
                    && user.getId_Usuario() == ct.getUsuario().getId_Usuario()) {
                int newCantidad = ct.getCantidad() + 1;
                ct.setCantidad(newCantidad);
                ct.setTotal(newCantidad);
                repositorio.actualizar(ct.getCantidad(), ct.getTotal(), ct.getCodigo_Carrito());
                ms = "Se añadio una unidad de este producto en el carrito";
                isIn = true;
                break;
            }
        }

        if (isIn == false) {
            Carrito c = new Carrito(product, user, cantidad);
            repositorio.save(c);
            if (repositorio.findById(c.getCodigo_Carrito()).isPresent()) {
                ms = "El producto se agrego a carrito";
            } else {
                ms = "El producto no se agrego a carrito";
            }
        }

        if (cotizaciones.isEmpty()) {
            Carrito c = new Carrito(product, user, cantidad);
            repositorio.save(c);
            if (repositorio.findById(c.getCodigo_Carrito()).isPresent()) {
                ms = "El producto se agrego a carrito";
            } else {
                ms = "El producto no se agrego a carrito";
            }
        }
        return ms;
    }
}
