package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Distribuir;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioDistribuir;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioVenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ServicioVenta {
    RepositorioVenta repositorio;
    RepositorioProducto repositorioProducto;
    RepositorioUsuario repositorioUsuario;
    RepositorioDistribuir repositorioDistribuir;


    public ServicioVenta(RepositorioVenta repository) {
        this.repositorio = repository;
    }

    public String insertar (Venta v) {
        Producto p =  v.getProducto();
        Usuario u = v.getUsuario();
        Distribuir d  = v.getDistribuir();
        String ms = "Alguno de los datos no existe";

        if(repositorioProducto.findById(p.getProd_Codigo()).isPresent() && repositorioUsuario.findById(u.getId_Usuario()).isPresent() && repositorioDistribuir.findById(d.getCodigo_dis()).isPresent()){
            repositorio.save(v);
            ms = "La venta se agrego correctamente";
        }
        return ms;
    }

    public ArrayList<Venta> listar () {
        return (ArrayList<Venta>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino la venta";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "La venta se elimino correctamente";
        }
        return ms;
    }
}
