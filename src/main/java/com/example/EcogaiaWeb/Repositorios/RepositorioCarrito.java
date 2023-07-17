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

    @Query(value = "select codigo_Carrito, prod_precio, cantidad, total, p.Prod_Nombre, c.id_Usuario from carrito as c inner join producto as p on p.Prod_Codigo = c.codigo_prod inner join usuario as u on u.id_usuario = c.id_usuario where u.usu_correo =:correo", nativeQuery = true)
    List<Object[]> cotizaciones(@Param("correo") String correo);
    @Query(value= "select codigo_carrito, cantidad,total,codigo_prod,c.id_usuario,Prod_Nombre from carrito as c inner join producto as p on p.Prod_Codigo = codigo_prod inner join usuario as u on u.id_Usuario = c.id_Usuario where u.usu_correo = :correo", nativeQuery = true )
    List<Object[]> buscar(@Param("correo")String correo);

    @Query(value = "select codigo_carrito, cantidad ,total , codigo_prod , u.id_usuario , p.prod_Nombre, u.usu_correo from carrito as c inner join producto as p on p.prod_Codigo = c.codigo_prod inner join usuario as u on u.id_usuario = c.id_usuario where u.usu_correo = :correo order by prod_nombre", nativeQuery = true)
    List<Object[]> sortCarName(@Param("correo")String correo);

    @Query(value = "select codigo_carrito, cantidad ,total , codigo_prod , u.id_usuario , p.prod_Nombre, u.usu_correo from carrito as c inner join producto as p on p.prod_Codigo = c.codigo_prod inner join usuario as u on u.id_usuario = c.id_usuario where u.usu_correo = :correo order by cantidad", nativeQuery = true)
    List<Object[]> sortCarCant(@Param("correo")String correo);

    @Query(value = "select codigo_carrito, cantidad ,total , codigo_prod , u.id_usuario , p.prod_Nombre, u.usu_correo from carrito as c inner join producto as p on p.prod_Codigo = c.codigo_prod inner join usuario as u on u.id_usuario = c.id_usuario where u.usu_correo = :correo order by total", nativeQuery = true)
    List<Object[]> sortCarTot(@Param("correo")String correo);


}
