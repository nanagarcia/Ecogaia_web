package com.example.EcogaiaWeb.Repositorios;

import com.example.EcogaiaWeb.Entidades.Orden_pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioOrden_pedido extends CrudRepository<Orden_pedido, Integer> {
}
