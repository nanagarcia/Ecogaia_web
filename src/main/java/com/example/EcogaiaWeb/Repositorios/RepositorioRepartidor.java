package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioRepartidor extends CrudRepository<Repartidor, Integer> {

    @Query(value = "select u.usu_nombre, u.usu_direccion, u.usu_telefono, u.id_Usuario, venta_codigo, prod_nombre from venta as v inner join usuario as u on u.id_usuario = v.id_usuario inner join detalle_venta as dt on codigo_venta = venta_codigo inner join producto as p on codigo_prod = prod_Codigo inner join repartidor as r on id_rep = id_repartidor where id_rep =:id", nativeQuery = true)
    List<Object[]> distribuir(@Param("id") Integer id_rep);

}
