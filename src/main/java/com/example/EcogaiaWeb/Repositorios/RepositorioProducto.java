package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto extends CrudRepository<Producto, Integer> {
}
