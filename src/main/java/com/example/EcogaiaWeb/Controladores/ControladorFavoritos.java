package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import com.example.EcogaiaWeb.Servicios.ServicioFavoritos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorFavoritos {
    ServicioFavoritos SF;

    public ControladorFavoritos(ServicioFavoritos serF) {
        this.SF = serF;
    }

    @PostMapping(path = "/insertarFavoritos/{codigo}/{id}")
    public Boolean insertar(@PathVariable("codigo") Integer codigo, @PathVariable("id") Integer id) {
        return SF.insertar(codigo, id);
    }

    @GetMapping(path = "/listarFavoritos")
    public ArrayList<Favoritos> listar() {
        return SF.listar();
    }

    @DeleteMapping("/eliminarFavoritos/{codigo}")
    public String eliminar(@PathVariable("codigo") Integer cod) {
        return SF.eliminar(cod);
    }

    @DeleteMapping("/eliminarTodoFavoritos/{id}")
    public String eliminarTod(@PathVariable("id") Integer id) {
        return SF.eliminarTodo(id);
    }

    @GetMapping("/favoritosUsuario/{correo}")
    public ResponseEntity<List<Map<String, String>>> favoritoUsuario(@PathVariable("correo") String correo) {
        List<Object[]> favoritos = SF.favoritosUsuario(correo);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        favoritos.forEach((favorito) -> {
            Map<String, String> datos = new HashMap<String, String>();
            String prod_imagen = "";
            if (favorito[3] != null) prod_imagen = favorito[3].toString();
            datos.put("prod_Codigo", favorito[0].toString());
            datos.put("prod_Cantidad", favorito[1].toString());
            datos.put("prod_Categoria", favorito[2].toString());
            datos.put("prod_Imagen", prod_imagen);
            datos.put("prod_Nombre", favorito[4].toString());
            datos.put("prod_Precio", favorito[5].toString());
            datos.put("codigo_favoritos", favorito[6].toString());
            mostrar.add(datos);
        });

        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }
}
