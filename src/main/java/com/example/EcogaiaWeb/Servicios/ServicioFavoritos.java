package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioFavoritos;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioFavoritos {
    RepositorioFavoritos repositorio;
    RepositorioProducto repositorioProducto;

    public ServicioFavoritos(RepositorioFavoritos repo, RepositorioProducto repositorioProducto) {
        this.repositorio = repo;
        this.repositorioProducto = repositorioProducto;
    }

    public Boolean insertar(Integer codigo, Integer id) {
        ArrayList<Favoritos> favoritos = this.listar();
        boolean res = true;

        for (Favoritos f : favoritos) {
            if (f.getUsuario().getId_Usuario() == id && f.getProducto().getProd_Codigo() == codigo) {
                repositorio.deleteById(f.getCodigo_favoritos());
                res = false;
                break;
            }
        }

        if (res) {
            repositorio.insertar(codigo, id);
        }

        return res;
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

    public String eliminarTodo(Integer id) {
        ArrayList<Favoritos> favoritos = this.listar();
        String ms = "No se limpio favoritos";
        for (Favoritos f : favoritos) {
            if (f.getUsuario().getId_Usuario() == id) {
                repositorio.delete(f);
                ms = "Se limpio favoritos";
            }
        }
        return ms;
    }

    public List<Object[]> filtrar (String titulo, String correo) {
        List<Object[]>  favoritos = this.favoritosUsuario(correo);
        List<Object[]> mostrar = new ArrayList<Object[]>();
        mostrar.clear();
        for (Object[] obj: favoritos){
            if (obj[4].toString().toLowerCase(Locale.ROOT).startsWith(titulo.toLowerCase(Locale.ROOT))){
                mostrar.add(obj);
            }
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarFavNombre (String correo){
        List<Object[]> favoritos = repositorio.sortFavName(correo);
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : favoritos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_favoritos",obj[0].toString());
            Datos.put("codigo_prod",obj[1].toString());
            Datos.put("id_usuario",obj[2].toString());
            Datos.put("prod_Nombre",obj[3].toString());
            Datos.put("prod_Precio",obj[4].toString());
            Datos.put("prod_Categoria",obj[5].toString());
            Datos.put("prod_Cantidad",obj[6].toString());


            mostrar.add(Datos);
        }
        return mostrar;
    }


    public List<Map<String,String>> ordenarFavPrecio (String correo){
        List<Object[]> favoritos = repositorio.sortFavPrice(correo);
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : favoritos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_favoritos",obj[0].toString());
            Datos.put("codigo_prod",obj[1].toString());
            Datos.put("id_usuario",obj[2].toString());
            Datos.put("prod_Nombre",obj[3].toString());
            Datos.put("prod_Precio",obj[4].toString());
            Datos.put("prod_Categoria",obj[5].toString());
            Datos.put("prod_Cantidad",obj[6].toString());

            mostrar.add(Datos);
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarFavCategoria (String correo){
        List<Object[]> favoritos = repositorio.sortFavCat(correo);
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : favoritos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_favoritos",obj[0].toString());
            Datos.put("codigo_Prod",obj[1].toString());
            Datos.put("id_Usuario",obj[2].toString());
            Datos.put("prod_Nombre",obj[3].toString());
            Datos.put("prod_Precio",obj[4].toString());
            Datos.put("prod_Categoria",obj[5].toString());
            Datos.put("prod_Cantidad",obj[6].toString());


            mostrar.add(Datos);
        }
        return mostrar;
    }

}
