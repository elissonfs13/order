package com.elissonfs.prd.externo_a.clients;

import com.elissonfs.prd.externo_a.pedidos.PedidoADTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "orderClient", url="localhost:8080")
public interface OrderClient {

  @PostMapping(value = "/pedidos")
  void enviaPedido(PedidoADTO pedido);
}
