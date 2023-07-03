package com.example.EcogaiaWeb.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.EcogaiaWeb.Entidades.Carrito;

import jakarta.transaction.Transactional;

@Repository
public interface RepositorioCarrito extends CrudRepository<Carrito, Integer>{
    
    @Modifying
    @Transactional
    @Query (value = "update carrito set cantidad =:cantidad, total =:total where codigo_carrito =:codigo", nativeQuery = true)
    int actualizar(@Param("cantidad") int cantidad, @Param("total") int total, @Param("codigo") Integer codigo);

    @Query(value = "select codigo_Carrito, cantidad, total, p.Prod_Nombre, c.id_Usuario from carrito as c inner join producto as p on p.Prod_Codigo = c.codigo_prod inner join usuario as u on u.id_usuario = c.id_usuario where u.usu_correo =:correo", nativeQuery = true)
    List<Object[]> cotizaciones(@Param("correo") String correo);
    @Query(value= "select codigo_carrito, cantidad,total,codigo_prod,c.id_usuario,Prod_Nombre from carrito as c inner join producto as p on p.Prod_Codigo = codigo_prod inner join usuario as u on u.id_Usuario = c.id_Usuario where u.usu_correo = :correo", nativeQuery = true )
    List<Object[]> buscar(@Param("correo")String correo);
}
