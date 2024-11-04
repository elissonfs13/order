package com.elissonfs.processor.rabbitmq;

import com.elissonfs.processor.pedidos.Pedido;
import com.elissonfs.processor.pedidos.dto.PedidoBDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationProducer {
	
	@Value("${topic.exchange.notification.name}")
  private final String topicExchangeNotificationName;

	private final RabbitTemplate rabbitTemplate;

  private final ModelMapper modelMapper;

  public NotificationProducer(String topicExchangeNotificationName, RabbitTemplate rabbitTemplate, ModelMapper modelMapper) {
    this.topicExchangeNotificationName = topicExchangeNotificationName;
    this.rabbitTemplate = rabbitTemplate;
    this.modelMapper = modelMapper;
  }

  public void send(final Pedido pedido) {
    PedidoBDTO pedidoB = modelMapper.map(pedido, PedidoBDTO.class);
    log.info("Processor - Pedido encaminhado para a fila notification: " + pedidoB.toString());
    rabbitTemplate.convertAndSend(topicExchangeNotificationName, "notifications.new", pedidoB);
  }

}
