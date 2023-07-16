package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Producto;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioProducto extends CrudRepository<Producto, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update producto set prod_cantidad =:cantidad, prod_categoria =:categoria, prod_imagen=:imagen, prod_nombre =:nombre, prod_precio =:precio where prod_codigo =:id", nativeQuery = true)
    public int actualizar(@Param("cantidad") int cantidad, @Param("categoria") String categoria, @Param("imagen") String imagen, @Param("nombre") String nombre, @Param("precio") int precio, @Param("id") Integer id);

    @Query(value = "select prod_Codigo,prod_Cantidad,prod_Categoria,prod_Imagen,prod_Nombre,prod_Precio from producto order by prod_Nombre", nativeQuery = true)
    List<Object[]> sortProdName();
    @Query(value = "select prod_Codigo,prod_Cantidad,prod_Categoria,prod_Imagen,prod_Nombre,prod_Precio from producto order by prod_Precio", nativeQuery = true)
    List<Object[]> sortProdPrice();
    @Query(value = "select prod_Codigo,prod_Cantidad,prod_Categoria,prod_Imagen,prod_Nombre,prod_Precio from producto order by prod_Categoria", nativeQuery = true)
    List<Object[]> sortProdCat();
    @Query(value = "select prod_Codigo,prod_Cantidad,prod_Categoria,prod_Imagen,prod_Nombre,prod_Precio from producto order by prod_Cantidad", nativeQuery = true)
    List<Object[]> sortProdStock();

}
