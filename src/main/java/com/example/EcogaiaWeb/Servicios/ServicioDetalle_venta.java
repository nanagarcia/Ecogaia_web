package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.detalle_venta;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioDetalle_venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioDetalle_venta {
    RepositorioDetalle_venta repositorio;
    RepositorioProducto repositorioProducto;

    public ServicioDetalle_venta(RepositorioDetalle_venta repo) {
        this.repositorio = repo;
    }

    public String insertar(detalle_venta co) {
        Producto p = co.getProducto();
        Venta v = co.getVenta();
        String ms = "Alguno de los datos no existe";

        if (repositorioProducto.findById(p.getProd_Codigo()).isPresent() && repositorio.findById(v.getVenta_Codigo()).isPresent()) {
            repositorio.save(co);
            ms = "El favorito se agrego exitosamente";
        }
        return ms;
    }

    public ArrayList<detalle_venta> listar() {
        return (ArrayList<detalle_venta>) repositorio.findAll();
    }

    public String eliminar(Integer codigo_cotizacion) {
        String ms = "No se elimino la cotizacion";
        if (repositorio.existsById(codigo_cotizacion)) {
            repositorio.deleteById(codigo_cotizacion);
            ms = "La cotizacion se elimino exitosamente";
        }
        return ms;
    }

    public List<Object[]> cotizacionesUsuario(String correo) {
        return repositorio.cotizaciones(correo);
    }


}
