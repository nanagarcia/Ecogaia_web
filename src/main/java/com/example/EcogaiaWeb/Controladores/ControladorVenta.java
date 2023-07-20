package com.example.EcogaiaWeb.Controladores;

import com.example.EcogaiaWeb.Entidades.Venta;
import com.example.EcogaiaWeb.Servicios.ServicioVenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControladorVenta {
    ServicioVenta SV;

    public ControladorVenta(ServicioVenta s) {
        this.SV = s;
    }

    @PostMapping(path = "/insertarVenta", consumes = "application/x-www-form-urlencoded")
    public String insertar(Venta v) {
        return SV.insertar(v);
    }

    @GetMapping(path = "/listarVenta")
    public ArrayList<Venta> listar() {
        return SV.listar();
    }

    @DeleteMapping("/eliminarVenta/{codigo}")
    public String eliminar(@PathVariable("codigo") int codigo) {
        return SV.eliminar(codigo);
    }

    @GetMapping("/ventasRepartidor/{correo}/{id}")
    public ResponseEntity<List<Map<String, String>>> ventas (@PathVariable("correo") String correo, @PathVariable("id") Integer id) {
        List<Object[]> ventas = SV.productosVenta(correo, id);
        List<Map<String, String>> mostrar = new ArrayList<Map<String, String>>();

        ventas.forEach((venta) -> {
            Map<String, String> datos = new HashMap<String, String>();
            datos.put("venta_codigo", venta[0].toString());
            datos.put("prod_codigo", venta[1].toString());
            datos.put("prod_nombre", venta[2].toString());
            datos.put("prod_precio", venta[3].toString());
            datos.put("prod_imagen", venta[4].toString());
            datos.put("prod_categoria", venta[5].toString());
            datos.put("prod_cantidad", venta[6].toString());

            mostrar.add(datos);
        });

        return ResponseEntity.status(HttpStatus.OK).body(mostrar);
    }

    @GetMapping("/ventasAnuales/{sdate}/{edate}")
    public double ventaAnual (@PathVariable("sdate") String sDate, @PathVariable("edate") String eDate) {
        return SV.ventas_anuales(sDate, eDate);
    }

    @GetMapping("/comprasUsuario/{correo}")
    public ResponseEntity<List<Map<String, String>>> compras (@PathVariable("correo") String correo) {
        return ResponseEntity.status(HttpStatus.OK).body(SV.comprasUsuario(correo));
    }
}
