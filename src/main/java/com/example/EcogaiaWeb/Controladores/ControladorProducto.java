package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Producto;
import com.example.EcogaiaWeb.Servicios.ServicioProducto;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

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
    public String guardarImagen(@PathVariable("codigo") Integer codigo, @RequestParam("file") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            Path dir = Paths.get("src//main//resources//uploads");
            String ruta = dir.toFile().getAbsolutePath();
            if (SP.productoCodigo(codigo).isPresent()) {
                Producto p = SP.productoCodigo(codigo).get();
                try {
                    byte[] bytesImagen = imagen.getBytes();
                    Path allPath = Paths.get(ruta + "//Prueba" + imagen.getOriginalFilename());
                    Files.write(allPath, bytesImagen);
                    p.setProd_Imagen(p.getProd_Codigo().toString() + "_" + imagen.getOriginalFilename());
                    SP.actualizar(p);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "El productos no existe, no se puede agregar imagen";
            }
        }

        return "La imagen se agrego correctamente";
    }
}
