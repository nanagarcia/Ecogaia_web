package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Favoritos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioFavoritos extends CrudRepository<Favoritos, Integer> {
    @Query(value = "select p.Prod_Codigo, p.Prod_Cantidad, p.Prod_Categoria, p.Prod_Imagen, p.Prod_Nombre, p.Prod_Precio, Codigo_favoritos from Favoritos as f inner join producto as p on p.Prod_Codigo = producto inner join usuario as u on u.id_Usuario = usuario where u.id_Usuario = :id")
    List<Object[]> favoritos(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "insert into favoritos (codigo_prod, id_usuario) values (:codigo, :id)", nativeQuery = true)
    int insertar (@Param("codigo") Integer codigo, @Param(("id")) Integer id);
}
