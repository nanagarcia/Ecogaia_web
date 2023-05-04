package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioUsuario extends CrudRepository<Usuario, Integer> {
}
