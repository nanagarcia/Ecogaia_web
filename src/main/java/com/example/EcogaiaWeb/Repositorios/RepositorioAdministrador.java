package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAdministrador extends CrudRepository<Administrador, Integer> {
}
