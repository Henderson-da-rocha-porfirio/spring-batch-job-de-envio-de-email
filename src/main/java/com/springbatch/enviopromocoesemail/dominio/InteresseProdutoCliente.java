package com.springbatch.enviopromocoesemail.dominio;

import lombok.Data;

@Data
public class InteresseProdutoCliente {

	private Cliente cliente;
	private Produto produto;

}
