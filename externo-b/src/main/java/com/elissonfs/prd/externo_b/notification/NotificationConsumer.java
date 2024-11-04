package com.elissonfs.prd.externo_b.notification;


import com.elissonfs.prd.externo_b.pedidos.PedidoBDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

	@RabbitListener(queues = {"${queue.notification.name}"}, containerFactory = "listenerContainerFactory")
    public void receive(final PedidoBDTO pedido) {
		log.info("Produto Externo B - Pedido recebido: " + pedido.toString());
    }

}
