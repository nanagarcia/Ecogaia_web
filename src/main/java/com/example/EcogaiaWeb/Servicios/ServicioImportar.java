package com.example.EcogaiaWeb.Servicios;
import com.example.EcogaiaWeb.Entidades.Importar;
import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Entidades.Proveedor;
import com.example.EcogaiaWeb.Repositorios.RepositorioImportar;
import com.example.EcogaiaWeb.Repositorios.RepositorioProducto;
import com.example.EcogaiaWeb.Repositorios.RepositorioProveedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioImportar {

    RepositorioImportar repositorio;
    RepositorioProducto repositorioProducto;
    RepositorioProveedor repositorioProveedor;

    public ServicioImportar(RepositorioImportar repository){this.repositorio = repository;}

    public String insertar (Importar e) {
        Producto p = e.getProducto();
        Proveedor pro = e.getProveedor();
        String ms = "Alguno de los datos no existe";

        if(repositorioProducto.findById(p.getProd_Codigo()).isPresent() && repositorioProveedor.findById(pro.getRUC()).isPresent()){
            repositorio.save(e);
            ms = "Exportar se agrego correctamente";
        }
        return ms;
    }

    public ArrayList<Importar> listar () {
        return (ArrayList<Importar>) repositorio.findAll();
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
