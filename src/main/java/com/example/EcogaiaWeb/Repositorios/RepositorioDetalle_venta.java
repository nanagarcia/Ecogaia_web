package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.detalle_venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioDetalle_venta extends CrudRepository<detalle_venta, Integer> {
    @Query(value = "select u.usu_nombre, u.usu_direccion, u.usu_telefono, v.id_Usuario, venta_codigo, id_repartidor from venta as v inner join usuario as u on v.id_usuario = u.id_usuario inner join repartidor as r on id_rep = id_repartidor where r.rep_nombre =:nombre  group by id_usuario;", nativeQuery = true)
    List<Object[]> distribuir(@Param("nombre") String nombre);
}
