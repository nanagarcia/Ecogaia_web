package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioProd_tips extends CrudRepository<Prod_tips, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into prod_tips (comp_usuario, cuerpo, fecha, titulo, id_usuario) select usu_nombre, :cuerpo, :fecha, :titulo, u.id_Usuario from usuario as u where usu_correo =:correo", nativeQuery = true)
    int insert(@Param("cuerpo") String cuerpo, @Param("fecha") Date fecha, @Param("titulo") String titulo, @Param("correo") String correo);

    @Query(value = "select codigo_tip, comp_usuario,fecha,id_usuario,titulo,cuerpo  from prod_tips order by comp_usuario", nativeQuery = true)
    List<Object[]> sortTipUs();
    @Query(value = "select codigo_tip, comp_usuario,fecha,id_usuario,titulo,cuerpo  from prod_tips order by fecha", nativeQuery = true)
    List<Object[]> sortTipDate();
    @Query(value = "select codigo_tip, comp_usuario,fecha,id_usuario,titulo,cuerpo  from prod_tips order by titulo", nativeQuery = true)
    List<Object[]> sortTipAz();


}
