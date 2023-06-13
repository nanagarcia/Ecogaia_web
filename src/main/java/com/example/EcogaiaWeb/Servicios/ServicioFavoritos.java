package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioFavoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioFavoritos {
    RepositorioFavoritos repositorio;
    RepositorioProducto repositorioProducto;

    public ServicioFavoritos(RepositorioFavoritos repo, RepositorioProducto repositorioProducto) {
        this.repositorio = repo;
        this.repositorioProducto = repositorioProducto;
    }

    public String insertar(Integer codigo, Integer id) {
        ArrayList<Favoritos> favoritos = this.listar();
        String ms = "El producto no se agrego a favoritos";
        for (Favoritos f: favoritos) {
            if (f.getProducto().getProd_Codigo() == codigo && f.getUsuario().getId_Usuario() == id) { 
                ms = "El producto ya esta en favoritos";
                break;
            } else {
                System.out.println(f.getProducto());
                System.out.println(f.getUsuario());
                repositorio.insertar(codigo, id);
                ms = "El producto se agrego";
            }
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

    public List<Object[]> favoritosUsuario(String correo) {
        return repositorio.favoritos(correo);
    }

    public String eliminarTodo (Integer id) {
        ArrayList<Favoritos> favoritos = this.listar();
        String ms = "No se limpio favoritos";
        for (Favoritos f: favoritos) {
            if (f.getUsuario().getId_Usuario() == id) {
                repositorio.delete(f);
                ms = "Se limpio favoritos";
            }
        }
        return ms;
    }
}
