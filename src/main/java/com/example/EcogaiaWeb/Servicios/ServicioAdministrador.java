package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Administrador;
import com.example.EcogaiaWeb.Repositorios.RepositorioAdministrador;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ServicioAdministrador {

    RepositorioAdministrador repositorio;

    public ServicioAdministrador( RepositorioAdministrador repository){this.repositorio = repository;}

    public String insertar (Administrador a) {
        repositorio.save(a);

        if (repositorio.findById(a.getId_admin()).isPresent()) {
            return "Se agrego el administrador " + a.getAdmin_nombre();
        } else {
            return "No se agrego el administrador";
        }
    }

    public ArrayList<Administrador> listar () {
        return (ArrayList<Administrador>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino el administrador";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "El administrador se elimino correctamente";
        }
        return ms;
    }
}
