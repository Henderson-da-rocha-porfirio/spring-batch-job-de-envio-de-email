package com.springbatch.enviopromocoesemail.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.enviopromocoesemail.job.EnvioPromocoesClientesScheduleJob;

/* 1. Classe que define a periodicidade e a execução do job.
*  2. EnvioPromocoesClientesScheduleJob = é referenciado aqui.
*  3. .storeDurably() = propriedade que permite os dados das execuções agendadas
*  4. Trigger = gatilho da execução
*  5. '.withIntervalInSeconds(60)' = execução do job a cada 60 segundos
*  6. '.withRepeatCount(2)' = gatilho disparado 2(vezes) + 1(vez) = 3 disparos
*  7. '.forJob(quartzJobDetail())' = informa qual o job que vai usar este trigger */

@Configuration
public class QuartzConfig {
	@Bean
	public JobDetail quartzJobDetail() {
		return JobBuilder
				.newJob(EnvioPromocoesClientesScheduleJob.class)
				.storeDurably()
				.build();
	}
	
	@Bean 
	public Trigger jobTrigger() {
		SimpleScheduleBuilder scheduleBuilder =
				SimpleScheduleBuilder
					.simpleSchedule()
					.withIntervalInSeconds(60)
					.withRepeatCount(2);
		return TriggerBuilder
				.newTrigger()
				.forJob(quartzJobDetail())
				.withSchedule(scheduleBuilder)
				.build();
	}
}
