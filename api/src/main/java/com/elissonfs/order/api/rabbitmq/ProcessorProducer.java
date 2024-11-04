package com.elissonfs.order.api.rabbitmq;

import com.elissonfs.order.api.pedidos.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessorProducer {
	
	@Value("${topic.exchange.name}")
  private final String topicExchangeName;

	private final RabbitTemplate rabbitTemplate;

  private final ModelMapper modelMapper;

  public ProcessorProducer(String topicExchangeName, RabbitTemplate rabbitTemplate, ModelMapper modelMapper) {
    this.topicExchangeName = topicExchangeName;
    this.rabbitTemplate = rabbitTemplate;
    this.modelMapper = modelMapper;
  }

  public void send(final Pedido pedido) {
    rabbitTemplate.convertAndSend(topicExchangeName, "processor.new", pedido);
    log.info("Pedido encaminhado para a fila processor: " + pedido.toString());
  }

}
