package com.elissonfs.order.api.pedidos;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

  Optional<Pedido> findByIdentificador(String identificador);
}
