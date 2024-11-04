package com.elissonfs.prd.externo_a;

import com.elissonfs.prd.externo_a.clients.OrderClient;
import com.elissonfs.prd.externo_a.pedidos.PedidoADTO;
import com.elissonfs.prd.externo_a.pedidos.ProdutoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class ExternoAApplication {

	private static OrderClient client;

	public ExternoAApplication(OrderClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExternoAApplication.class, args);
		novosPedidos();
	}

	private static void novosPedidos() {
		int qtdPedidos = 200000;
		log.info("PRD EXTERNO A: Iniciando a geração de novos produtos");

		for (int i = 1; i <= qtdPedidos; i++) {
			PedidoADTO pedido = PedidoADTO.builder()
					.identificador("pedido-" + i)
					.produtos(novosProdutos(i))
					.build();

			log.info("Pedido criado: " + pedido);
			client.enviaPedido(pedido);
		}

	}

	private static List<ProdutoDTO> novosProdutos(int i) {
		List<ProdutoDTO> produtos = new ArrayList<>();
		produtos.add(ProdutoDTO.builder().nome("primeiro-produto-"+i).valor(new BigDecimal(i)).build());
		produtos.add(ProdutoDTO.builder().nome("segundo-produto-"+i).valor(new BigDecimal(i+1)).build());
		produtos.add(ProdutoDTO.builder().nome("terceiro-produto-"+i).valor(new BigDecimal(i+2)).build());
		return produtos;
	}

}
