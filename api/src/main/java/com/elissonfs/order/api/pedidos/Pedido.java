package com.elissonfs.order.api.pedidos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection="pedido")
public class Pedido {

  @Id
  private String id;

  private String identificador;

  private List<Produto> produtos;

  private StatusPedido status;

  private BigDecimal valorTotal;

  public void processaPedido() {
    this.valorTotal = calculaValorTotal();
    this.status = StatusPedido.PROCESSADO;
  }

  private BigDecimal calculaValorTotal() {
    BigDecimal total = BigDecimal.ZERO;
    for (Produto produto : this.produtos) {
      total = total.add(produto.getValor());
    }
    return total;
  }

}
