package com.elissonfs.processor.pedidos;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

  Optional<Pedido> findByIdentificadorAndStatus(String identificador, StatusPedido status);
}
