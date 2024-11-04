package com.elissonfs.order.api.pedidos;

import com.elissonfs.order.api.exception.OrderException;
import com.elissonfs.order.api.rabbitmq.ProcessorProducer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

  private final PedidoRepository repository;

  private final ProcessorProducer processorProducer;

  public PedidoService(PedidoRepository repository, ProcessorProducer notificationProducer) {
    this.repository = repository;
    this.processorProducer = notificationProducer;
  }

  public void recebePedido(Pedido novoPedido) {
    Optional<Pedido> optPedidoSalvo = repository.findByIdentificador(novoPedido.getIdentificador());

    if (optPedidoSalvo.isPresent()) {
      throw new OrderException("Pedido já cadastrado");
    }

    Pedido pedidoSalvo = repository.save(novoPedido);
    processorProducer.send(pedidoSalvo);
  }
}
