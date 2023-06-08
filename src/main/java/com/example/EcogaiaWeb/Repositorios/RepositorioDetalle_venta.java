package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.detalle_venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioDetalle_venta extends CrudRepository<detalle_venta, Integer> {
    @Query(value = "select codigo_cotizacion, cantidad, total, p.Prod_Nombre, v.Venta_Fecha, v.Venta_Codigo, usuario.id_Usuario from detalle_venta as dv inner join producto as p on p.Prod_Codigo = producto inner join venta as v on v.Venta_Codigo = venta where usuario.usu_correo =:correo")
    List<Object[]> cotizaciones(@Param("correo") String correo);


}
