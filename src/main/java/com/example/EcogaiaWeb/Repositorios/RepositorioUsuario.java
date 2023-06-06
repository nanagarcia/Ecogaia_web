package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Integer> {
    @Modifying
    @Transactional
    @Query("update Usuario u set u.usu_nombre =:nombre, u.usu_telefono =:telefono, u.usu_direccion =:direccion, u.usu_correo =:correo, u.usu_contrasenia =:contrasenia where u.usu_correo =:val")
    int actualizar(@Param("nombre") String nombre, @Param("telefono") String telefono, @Param("direccion") String direccion, @Param("correo") String correo, @Param("contrasenia") String contrasenia, @Param("val") String val);

    @Query(value = "select u.usu_nombre, u.usu_correo, u.usu_direccion, u.usu_contrasenia, u.usu_telefono from Usuario u where usu_correo =:correo")
    List<Object[]> usuario(@Param("correo") String correo);
}
