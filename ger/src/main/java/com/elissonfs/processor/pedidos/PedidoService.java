package com.elissonfs.processor.pedidos;

import com.elissonfs.processor.rabbitmq.NotificationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PedidoService {

  private final PedidoRepository repository;

  private final NotificationProducer notificationProducer;

  public PedidoService(PedidoRepository repository, NotificationProducer notificationProducer) {
    this.repository = repository;
    this.notificationProducer = notificationProducer;
  }

  public void processaPedido(Pedido pedido) {
    Optional<Pedido> optPedidoSalvo = repository.findByIdentificadorAndStatus(pedido.getIdentificador(), StatusPedido.NOVO);

    if (optPedidoSalvo.isPresent()) {
      Pedido pedidoSalvo = optPedidoSalvo.get();
      pedidoSalvo.processaPedido();
      log.info("Processor - Pedido processado: " + pedidoSalvo);
      repository.save(pedidoSalvo);
      notificationProducer.send(pedidoSalvo);
    }
  }
}
