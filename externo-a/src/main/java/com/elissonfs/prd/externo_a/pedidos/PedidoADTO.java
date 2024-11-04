package com.elissonfs.prd.externo_a.pedidos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PedidoADTO {

  private String identificador;

  private List<ProdutoDTO> produtos;

}
