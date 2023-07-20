package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.*;

@Service
public class ServicioProducto {
    RepositorioProducto repositorio;

    private ArrayList<Producto> mostrar = new ArrayList<Producto>();

    public ServicioProducto(RepositorioProducto repository) {
        this.repositorio = repository;
    }

    public String insertar(Producto p) {
        ArrayList<Producto> productos = this.listar();
        String ms = "";
        boolean isIn = false;
        for (Producto producto: productos) {
            if (producto.getProd_Nombre().equals(p.getProd_Nombre())) {
                isIn = true;
                break;
            }
        }

        if (isIn) {
            ms = "El producto ya se encuentra registrado";
        }else {
            repositorio.save(p);
            if (repositorio.findById(p.getProd_Codigo()).isPresent()) {
                ms = "El producto se registro correctamente";
            } else {
                ms = "El producto  no se registro";
            }
        }

        return ms;
    }

    public Optional<Producto> productoCodigo(Integer codigo) {
        if (repositorio.findById(codigo).isPresent()) {
            return repositorio.findById(codigo);
        } else {
            return Optional.empty();
        }
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

    public ArrayList<Producto> productoNombre(String nombre) {
        ArrayList<Producto> productos = this.listar();
        this.mostrar.clear();
        for (Producto p : productos) {
            if (p.getProd_Nombre().toLowerCase(Locale.ROOT).startsWith(nombre.toLowerCase(Locale.ROOT))) {
                this.mostrar.add(p);
            }
        }
        return this.mostrar;
    }

    public String actualizar (Producto p) {
        if (repositorio.findById(p.getProd_Codigo()).isPresent()) {
            repositorio.save(p);
            return "El producto se actualizo exitosamente";
        } else {
            return "El producto no existe";
        }
    }
    public List<Map<String,String>> ordenarNombre (){
        List<Object[]> productos = repositorio.sortProdName();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : productos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("prod_Codigo",obj[0].toString());
            Datos.put("prod_Cantidad",obj[1].toString());
            Datos.put("prod_Categoria",obj[2].toString());
            Datos.put("prod_Imagen",obj[3].toString());
            Datos.put("prod_Nombre",obj[4].toString());
            Datos.put("prod_Precio",obj[5].toString());

            mostrar.add(Datos);
        }
    return mostrar;
    }
    public List<Map<String,String>> ordenaarPrecio (){
        List<Object[]> productos = repositorio.sortProdPrice();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : productos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("prod_Codigo",obj[0].toString());
            Datos.put("prod_Cantidad",obj[1].toString());
            Datos.put("prod_Categoria",obj[2].toString());
            Datos.put("prod_Imagen",obj[3].toString());
            Datos.put("prod_Nombre",obj[4].toString());
            Datos.put("prod_Precio",obj[5].toString());

            mostrar.add(Datos);
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarCategoria (){
        List<Object[]> productos = repositorio.sortProdCat();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : productos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("prod_Codigo",obj[0].toString());
            Datos.put("prod_Cantidad",obj[1].toString());
            Datos.put("prod_Categoria",obj[2].toString());
            Datos.put("prod_Imagen",obj[3].toString());
            Datos.put("prod_Nombre",obj[4].toString());
            Datos.put("prod_Precio",obj[5].toString());

            mostrar.add(Datos);
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarCantidad (){
        List<Object[]> productos = repositorio.sortProdStock();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : productos){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("prod_Codigo",obj[0].toString());
            Datos.put("prod_Cantidad",obj[1].toString());
            Datos.put("prod_Categoria",obj[2].toString());
            Datos.put("prod_Imagen",obj[3].toString());
            Datos.put("prod_Nombre",obj[4].toString());
            Datos.put("prod_Precio",obj[5].toString());

            mostrar.add(Datos);
        }
        return mostrar;
    }

}
