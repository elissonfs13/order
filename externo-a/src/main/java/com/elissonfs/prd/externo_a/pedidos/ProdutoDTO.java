package com.elissonfs.prd.externo_a.pedidos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoDTO {

  private String nome;

  private BigDecimal valor;

}
