package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {
    @Query(value = "select usu_nombre, usu_direccion, usu_telefono, sum(cantidad) from Venta v inner join detalle_venta as dt on Venta_Codigo = v.detalleventa inner join usuario as u on u.id_Usuario = v.usuario where repartidor =:id")
    List<Object> ventas (@Param("id") Integer id);
}
