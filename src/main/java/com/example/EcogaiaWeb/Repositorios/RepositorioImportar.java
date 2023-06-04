package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Importar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioImportar extends CrudRepository<Importar, Integer> {
}
