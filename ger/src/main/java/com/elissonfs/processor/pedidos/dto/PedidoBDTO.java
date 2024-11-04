package com.elissonfs.processor.pedidos.dto;

import com.elissonfs.processor.pedidos.StatusPedido;
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
