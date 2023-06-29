package com.example.EcogaiaWeb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class routes {
    @GetMapping("/")
    public String home() {
        return "views/index";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "views/nosotros";
    }

    @GetMapping("/blog")
    public String blog() {
        return "views/Blog";
    }

    @GetMapping("/inventario")
    public String inventario() {
        return "views/inventarioEco";
    }

    @GetMapping("/estadisticas")
    public String estadisticas() {
        return "views/estadisticas";
    }

    @GetMapping("/distribuciones")
    public String repartidor() {
        return "views/repartidor";
    }

    @GetMapping("/registrarse")
    public String registrarse() {
        return "views/registrarse";
    }

    @GetMapping("/login")
    public String login() {
        return "views/iniciosesion";
    }

    @GetMapping("/user")
    public String usuario() {
        return "views/perfil";
    }

    @GetMapping("/post")
    public String post() {
        return "views/insertar_blog";
    }

    @GetMapping("/actualizar-usuario")
    public String actualizar() {
        return "views/actualizacion";
    }
}
