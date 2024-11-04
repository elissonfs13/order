package com.elissonfs.processor.pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

  private String nome;

  private BigDecimal valor;

}
