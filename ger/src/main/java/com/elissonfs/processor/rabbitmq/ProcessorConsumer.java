package com.elissonfs.processor.rabbitmq;


import com.elissonfs.processor.pedidos.Pedido;
import com.elissonfs.processor.pedidos.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessorConsumer {

  private final PedidoService service;

  public ProcessorConsumer(PedidoService service) {
    this.service = service;
  }

  @RabbitListener(queues = {"${queue.processor.name}"}, containerFactory = "listenerContainerFactory")
  public void receive(final Pedido pedido) {
		log.info("Processor - Pedido recebido: " + pedido.toString());
    service.processaPedido(pedido);
  }

}
