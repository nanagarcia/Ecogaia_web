package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Servicios.ServicioRepartidor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorRepartidor {
    ServicioRepartidor SR;

    public ControladorRepartidor(ServicioRepartidor repartidor){
        this.SR = repartidor;
    }

    @PostMapping(path = "/insertarrepartidor", consumes = "application/x-www-form-urlencoded")
    public String insertar( Repartidor R){
        return SR.insertar(R);
    }

    @GetMapping(path = "/listarRepartidor")
    public ArrayList<Repartidor> listar(){
        return SR.listar();
    }

    @DeleteMapping("/eliminarRepartidor/{id}")
    public String eliminar(@PathVariable("id")int id){
        return SR.eliminar(id);
    }

    @GetMapping("/distribuir/{id}")
    public ResponseEntity<List<Map<String, String>>> distribuir (@PathVariable("id") Integer id_rep) {
        List<Object[]> distribuciones = SR.distribuciones(id_rep);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] objects: distribuciones) {
            Map<String, String> datos = new HashMap<>();
            datos.put("usu_nombre", objects[0].toString());
            datos.put("usu_direccion", objects[1].toString());
            datos.put("usu_telefono", objects[2].toString());
            datos.put("id_Usuario", objects[3].toString());
            datos.put("venta_Codigo", objects[4].toString());
            datos.put("prod_nombre", objects[5].toString());
            mostrar.add(datos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }
}
