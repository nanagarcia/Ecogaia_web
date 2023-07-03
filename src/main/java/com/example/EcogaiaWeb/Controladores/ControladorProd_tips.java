package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import com.example.EcogaiaWeb.Servicios.ServicioProd_tips;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorProd_tips {

    ServicioProd_tips SPR;

    public ControladorProd_tips(ServicioProd_tips s){
        this.SPR = s;
    }

    @PostMapping(path = "/insertarTip/{correo}", consumes = "application/x-www-form-urlencoded")
    public String insertar(@PathVariable("correo") String correo, Prod_tips pt) {
        return SPR.insertar(correo, pt);
    }

    @GetMapping(path = "/listarTip")
    public ArrayList<Prod_tips> listar() {
        return SPR.listar();
    }

    @DeleteMapping("/eliminarTip/{id}")
    public String eliminar(@PathVariable("id") int codigo){
        return SPR.eliminar(codigo);
    }

    @GetMapping(path = "/tituloTip/{titulo}")
    public ArrayList<Prod_tips> titulo (
            @PathVariable("titulo") String titulo) {
        return SPR.titulo(titulo);
    }

    @GetMapping("/tipsUsuario/{correo}")
    public ArrayList<Prod_tips> tipsUsuario (@PathVariable("correo") String correo) {
        return SPR.tipsUsuario(correo);
    }
}
