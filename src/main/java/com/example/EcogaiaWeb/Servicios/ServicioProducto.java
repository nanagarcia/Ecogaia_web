package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

@Service
public class ServicioProducto {
    RepositorioProducto repositorio;

    private ArrayList<Producto> mostrar = new ArrayList<Producto>();

    public ServicioProducto(RepositorioProducto repository) {
        this.repositorio = repository;
    }

    public String insertar(Producto p) {
        repositorio.save(p);
        if(repositorio.findById(p.getProd_Codigo()).isPresent()) {
            return "El producto se agrego";
        } else {
            return "El producto no se agrego";
        } 
    }

    public Optional<Producto> productoId(Integer codigo) {
        Optional<Producto> p = repositorio.findById(codigo);
        return p;
    }

    public ArrayList<Producto> listar() {
        return (ArrayList<Producto>) repositorio.findAll();
    }

    public String eliminar(Integer codigo) {
        String ms = "No se pudo eliminar el producto";
        if (repositorio.existsById(codigo)) {
            repositorio.deleteById(codigo);
            ms = "El producto se elimino correctamente";
        }
        return ms;
    }

    public ArrayList<Producto> categoria(String cat) {
        ArrayList<Producto> productos = this.listar();
        this.mostrar.clear();
        for (Producto i : productos) {
            if (i.getProd_Categoria().equals(cat)) {
                this.mostrar.add(i);
            }
        }
        return this.mostrar;
    }

<<<<<<< HEAD
    public ArrayList<Producto>prod_nombre (String Nombre) {
        ArrayList<Producto> productos = (ArrayList<Producto>) repositorio.findAll();
        mostrar.clear();
        for (Producto i: productos){
            if (i.getProd_Nombre().toLowerCase(Locale.ROOT).startsWith(Nombre.toLowerCase(Locale.ROOT))){
                mostrar.add(i);
            }
        }
        return mostrar;
=======
    public ArrayList<Producto> productoNombre(String nombre) {
        ArrayList<Producto> productos = this.listar();
        this.mostrar.clear();
        for (Producto p : productos) {
            if (p.getProd_Nombre().toLowerCase(Locale.ROOT).startsWith(nombre.toLowerCase(Locale.ROOT))) {
                this.mostrar.add(p);
            }
        }
        return this.mostrar;
>>>>>>> 183725c4538fe6f59b850f9ae59181ac16a2f039
    }
}
