package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {
    @Query(value = "select venta_codigo, prod_codigo, prod_nombre, prod_precio, prod_categoria, prod_cantidad from venta as v inner join detalle_venta as dt on codigo_venta = venta_codigo inner join producto as p on codigo_prod = prod_codigo inner join repartidor as r on r.id_rep = v.id_repartidor where rep_nombre =:nombre and venta_estado = \"En distribucion\" and id_usuario =:id ;", nativeQuery = true)
    List<Object[]> ventas (@Param("nombre") String nombre, @Param("id") Integer id);

    @Query(value = "select count(*) as cantidad from venta where venta_fecha > :sdate and venta_fecha < :edate",nativeQuery = true)
    List<Object[]> anuales(@Param("sdate") String sdate, @Param("edate") String edate);

    @Query(value = "select usu_nombre,venta_estado, venta_codigo, venta_fecha, rep_nombre from venta as v inner join usuario as u on u.id_usuario = v.id_usuario inner join repartidor on id_rep = id_repartidor where usu_correo =:correo", nativeQuery = true)
    List<Object[]> compras(@Param("correo") String correo);
}
