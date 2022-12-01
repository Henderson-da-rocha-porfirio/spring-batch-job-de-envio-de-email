package com.springbatch.enviopromocoesemail.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*  - Classe que permite a customização do job.
*  1. Job Schedule = agendador, ou seja, executa o job com o jobLauncher.
*  2. Quartz = definir quando a execução do job vai ocorrer.
*  3. JobExplorer = permite a navegação através de execuções anteriores para obter os parâmetros que vão ser utilizados
* nessa execução. E ele se faz necessário para construir o builder de parâmetros de job.
*  4. Para que o Spring utilize esse executor e não o padrão do spring boot, é preciso colocar em
* application.properties = spring.batch.job.enabled=false
*  5. Para configurar o Quartz, é preciso criar uma classe para isso para definir a periodicidade e a execução do job.
* Por ex: QuartzConfig   */

public class EnvioPromocoesClientesScheduleJob extends QuartzJobBean {
	@Autowired
	private Job job;
	@Autowired
	private JobExplorer jobExplorer;
	@Autowired
	private JobLauncher jobLauncher;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer).getNextJobParameters(this.job).toJobParameters();
		try {
			this.jobLauncher.run(this.job, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
