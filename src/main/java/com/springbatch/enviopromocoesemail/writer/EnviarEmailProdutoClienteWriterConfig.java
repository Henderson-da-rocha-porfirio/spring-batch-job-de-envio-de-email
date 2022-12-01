package com.springbatch.enviopromocoesemail.writer;

import org.springframework.batch.item.mail.SimpleMailMessageItemWriter;
import org.springframework.batch.item.mail.builder.SimpleMailMessageItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

/* 1. MailSender = Este Bean do tipo mailSender não precisou ser configurado porque quem configura é o próprio Spring,
* porque ele obteve as propriedades do application.properties preenchidas e carregou alí no MailSender */

@Configuration
public class EnviarEmailProdutoClienteWriterConfig {
	@Bean
	public SimpleMailMessageItemWriter enviarEmailProdutoClienteWriter(MailSender mailSender) {
		return new SimpleMailMessageItemWriterBuilder()
				.mailSender(mailSender)
				.build();
	}
}
