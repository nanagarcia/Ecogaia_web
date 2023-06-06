package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProveedor extends CrudRepository<Proveedor, Integer> {
}
