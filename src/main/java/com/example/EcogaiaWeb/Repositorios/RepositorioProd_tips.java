package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProd_tips extends CrudRepository<Prod_tips, Integer> {
}
