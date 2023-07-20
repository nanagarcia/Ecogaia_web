package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Servicios.ServicioProducto;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorProducto {
    ServicioProducto SP;

    public ControladorProducto(ServicioProducto s) {
        this.SP = s;
    }

    @PostMapping(path = "/insertarProducto", consumes = "application/x-www-form-urlencoded")
    public String insertar(Producto p) {
        return SP.insertar(p);
    }

    @GetMapping(path = "/listarProducto")
    public ArrayList<Producto> listar() {
        return SP.listar();
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable("id") int codigo) {
        return SP.eliminar(codigo);
    }

    @GetMapping("/categoriasProducto/{cat}")
    public ArrayList<Producto> categorias(@PathVariable("cat") String cat) {
        return SP.categoria(cat);
    }

    @GetMapping("/nombreProducto/{nombre}")
    public ArrayList<Producto> nombre(@PathVariable("nombre") String nombre) {
        return SP.productoNombre(nombre);
    }

    @PutMapping("/actualizarProducto")
    public String actualizar (Producto p) {
        return SP.actualizar(p);
    }

    @GetMapping("/productoCodigo/{codigo}")
    public Optional<Producto> codigo (@PathVariable("codigo") Integer codigo) {
        return SP.productoCodigo(codigo);
    }

    @PostMapping("/guardarImagen/{codigo}")
    public String guardarImagen(@PathVariable("codigo") Integer codigo) {
        Producto p = SP.productoCodigo(codigo).get();
        p.setProd_Imagen("https://www.hods.eu/wp-content/uploads/vasopla_hods_web_01.jpg");
        SP.actualizar(p);
        if (Objects.equals(SP.productoCodigo(p.getProd_Codigo()).get().getProd_Imagen(), "https://www.hods.eu/wp-content/uploads/vasopla_hods_web_01.jpg")) {
            return "La imagen se actualizo";
        } else {
            return "La imagen no se pudo actualizar";
        }
    }
    @GetMapping("/ordenarProdNombre")
    public ResponseEntity<List<Map<String, String>>> ordenarProdNombre () {
        return ResponseEntity.status(HttpStatus.OK).body(SP.ordenarNombre());
    }
    @GetMapping("/ordenarProdPrecio")
    public ResponseEntity<List<Map<String, String>>> ordenarProdPrecio () {
        return ResponseEntity.status(HttpStatus.OK).body(SP.ordenaarPrecio());
    }
    @GetMapping("/ordenarProdCategoria")
    public ResponseEntity<List<Map<String, String>>> ordenarProdCategoria () {
        return ResponseEntity.status(HttpStatus.OK).body(SP.ordenarCategoria());
    }
    @GetMapping("/ordenarProdCantidad")
    public ResponseEntity<List<Map<String, String>>> ordenarProdCantidad () {
        return ResponseEntity.status(HttpStatus.OK).body(SP.ordenarCantidad());
    }

}
