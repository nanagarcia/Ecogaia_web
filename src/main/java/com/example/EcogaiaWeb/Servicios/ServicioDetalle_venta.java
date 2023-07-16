package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.detalle_venta;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioDetalle_venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioRepartidor;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioDetalle_venta {
    RepositorioDetalle_venta repositorio;
    RepositorioUsuario repositorioUsuario;
    RepositorioRepartidor repositorioRepartidor;
    RepositorioProducto repositorioProducto;

    public ServicioDetalle_venta(RepositorioDetalle_venta repo, RepositorioUsuario usuario, RepositorioRepartidor repositorioRepartidor, RepositorioProducto repositorioProducto) {
        this.repositorio = repo;
        this.repositorioUsuario = usuario;
        this.repositorioRepartidor = repositorioRepartidor;
        this.repositorioProducto =  repositorioProducto;
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

    public List<Object[]> distribuciones(String correo) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) this.repositorioUsuario.findAll();
        ArrayList<Repartidor> repartidores = (ArrayList<Repartidor>) this.repositorioRepartidor.findAll();

        List<Object[]> mostrar = new ArrayList<Object[]>();
        for (Usuario u: usuarios) {
            if (u.getUsu_correo().equals(correo)) {
                for (Repartidor rep: repartidores) {
                    if (rep.getRep_Nombre().equals(u.getUsu_nombre())) {
                        mostrar = repositorio.distribuir(rep.getRep_Nombre());
                    }
                };
            }
        }

        return mostrar;
    }

    public List<Map<String, String>> listadoProductosDesc () {
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();
        List<Object[]> productos = repositorio.productosDesc();

        for (Object[] objects: productos) {
            Map<String, String> producto = new HashMap<String, String>();
            producto.put("cantidad", objects[0].toString());
            producto.put("prod_Nombre", objects[1].toString());
            producto.put("codigo_Prod", objects[2].toString());

            mostrar.add(producto);
        }

        return mostrar;
    }

    public List<Map<String, String>> listadoProductosAsc () {
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();
        List<Object[]> productos = repositorio.productosAsc();

        for (Object[] objects: productos) {
            Map<String, String> producto = new HashMap<String, String>();
            producto.put("cantidad", objects[0].toString());
            producto.put("prod_Nombre", objects[1].toString());
            producto.put("codigo_Prod", objects[2].toString());

            mostrar.add(producto);
        }

        return mostrar;
    }


    public List<Map<String, String>> productos (String correo, Integer codigo) {
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();
        List<Object[]> productos = repositorio.productos(correo, codigo);

        for (Object[] objects: productos) {
            Map<String, String> producto = new HashMap<String, String>();
            producto.put("prod_codigo", objects[0].toString());
            producto.put("prod_nombre", objects[1].toString());
            producto.put("prod_precio", objects[2].toString());
            producto.put("prod_categoria", objects[3].toString());
            producto.put("prod_cantidad", objects[4].toString());
            producto.put("id_usuario", objects[5].toString());
            producto.put("venta_codigo", objects[6].toString());

            mostrar.add(producto);
        }

        return  mostrar;
    }
}
