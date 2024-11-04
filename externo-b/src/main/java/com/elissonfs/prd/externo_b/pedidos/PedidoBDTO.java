package com.elissonfs.prd.externo_b.pedidos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoBDTO {

  private String identificador;

  private List<ProdutoDTO> produtos;

  private StatusPedido status;

  private BigDecimal valorTotal;

}
