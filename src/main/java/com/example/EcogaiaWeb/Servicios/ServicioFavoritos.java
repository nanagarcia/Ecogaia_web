package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioFavoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioFavoritos {
    RepositorioFavoritos repositorio;
    ServicioUsuario SU;
    ServicioProducto SP;

    public ServicioFavoritos(RepositorioFavoritos repo) {
        this.repositorio = repo;
    }

    public String insertar(Favoritos F) {
        Usuario u = F.getUsuario();
        Producto p = F.getProducto();
        String ms = "Alguno de los datos no existe";
        RepositorioProducto repositorioProducto = SP.repositorio;
        RepositorioUsuario repositorioUsuario = SU.repositorio;

        if (repositorioUsuario.findById(u.getId_Usuario()).isPresent() && repositorioProducto.findById(p.getProd_Codigo()).isPresent()) {
            repositorio.save(F);
            ms = "El favorito se agrego exitosamente";
        }
        return ms;

    }

    public ArrayList<Favoritos> listar() {
        return (ArrayList<Favoritos>) repositorio.findAll();
    }

    public String eliminar(Integer codigo_favoritos) {
        String ms = "No se elimino de favoritos";
        if (repositorio.existsById(codigo_favoritos)) {
            repositorio.deleteById(codigo_favoritos);
            ms = "Se elimino de favoritos";
        }
        return ms;
    }

    public List<Object[]> favoritosUsuario(Integer id) {
        return repositorio.favoritos(id);
    }
}
