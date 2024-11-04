package com.elissonfs.order.api.api;

import com.elissonfs.order.api.exception.OrderException;
import com.elissonfs.order.api.pedidos.Pedido;
import com.elissonfs.order.api.pedidos.PedidoService;
import com.elissonfs.order.api.pedidos.StatusPedido;
import com.elissonfs.order.api.pedidos.dto.PedidoADTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService service;

  private final ModelMapper modelMapper;

  public PedidoController(PedidoService service, ModelMapper modelMapper) {
    this.service = service;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity receberPedidoDeProdutoExternoA(@RequestBody PedidoADTO pedido) {
    log.info("API - Pedido recebido: " + pedido.toString());
    Pedido novoPedido = modelMapper.map(pedido, Pedido.class);
    novoPedido.setStatus(StatusPedido.NOVO);

    try {
      service.recebePedido(novoPedido);
      return ResponseEntity.ok().build();
    } catch (OrderException exception) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
  }

}
