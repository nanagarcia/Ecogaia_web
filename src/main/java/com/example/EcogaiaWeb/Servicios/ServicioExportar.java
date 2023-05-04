package com.example.EcogaiaWeb.Servicios;
import com.example.EcogaiaWeb.Entidades.Exportar;
import com.example.EcogaiaWeb.Repositorios.RepositorioExportar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioExportar {

    RepositorioExportar repositorio;

    public ServicioExportar( RepositorioExportar repository){this.repositorio = repository;}

    public String insertar (Exportar e) {
        repositorio.save(e);
        return "Se agrego correctamente";
    }

    public ArrayList<Exportar> listar () {
        return (ArrayList<Exportar>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino Exportar";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "El exportar se elimino correctamente";
        }
        return ms;
    }
}
