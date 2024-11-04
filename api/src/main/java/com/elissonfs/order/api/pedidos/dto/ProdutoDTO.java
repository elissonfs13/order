package com.elissonfs.order.api.pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

  private String nome;

  private BigDecimal valor;

}
