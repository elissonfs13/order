package com.elissonfs.order.api.pedidos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection="produto")
public class Produto {

  @Id
  private String id;

  private String nome;

  private BigDecimal valor;

}
