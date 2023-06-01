package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Repartidor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRepartidor extends CrudRepository<Repartidor, Integer> {


}
