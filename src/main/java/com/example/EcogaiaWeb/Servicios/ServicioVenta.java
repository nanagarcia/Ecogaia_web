package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioVenta;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class ServicioVenta {
    RepositorioVenta repositorio;
    RepositorioUsuario repositorioUsuario;

    public ServicioVenta(RepositorioVenta repository, RepositorioUsuario repositorioUsuario) {
        this.repositorio = repository;
        this.repositorioUsuario = repositorioUsuario;
    }

    public String insertar (Venta v) {
        repositorio.save(v);
        return "bien";
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

    public List<Object[]> productosVenta (String correo, Integer id) {
        List<Object[]> usuario = repositorioUsuario.usuario(correo);
        String nombre = usuario.get(0)[1].toString();
        return repositorio.ventas(nombre, id);
    }

    public double ventas_anuales (String startDate, String endDate) {
        float cantVentas = ((ArrayList<Venta>) repositorio.findAll()).size();
        List<Object[]> ventas = repositorio.anuales(startDate, endDate);
        List<Map<String,String>> mostrar = new ArrayList<Map<String,String>>();

        for (Object[] objects: ventas) {
            Map<String, String> datos = new HashMap<String, String>();
            datos.put("cantidad", objects[0].toString());
            mostrar.add(datos);
        }

        float cantidad = Integer.parseInt(mostrar.get(0).get("cantidad"));

        return (cantidad / cantVentas) * 100;
    }

    public List<Map<String, String>> comprasUsuario (String correo) {
        List<Object[]> compras = repositorio.compras(correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] objects: compras) {
            Map<String, String> datos = new HashMap<String, String>();
            datos.put("usu_nombre", objects[0].toString());
            datos.put("venta_estado",objects[1].toString());
            datos.put("venta_codigo",objects[2].toString());
            datos.put("venta_fecha",objects[3].toString());
            datos.put("rep_nombre",objects[4].toString());

            mostrar.add(datos);
        }

        return mostrar;
    }
}
