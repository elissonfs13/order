package com.elissonfs.order.api.pedidos.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoADTO {

  private String identificador;

  private List<ProdutoDTO> produtos;

}
