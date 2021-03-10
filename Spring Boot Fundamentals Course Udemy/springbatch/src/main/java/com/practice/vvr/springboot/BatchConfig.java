package com.practice.vvr.springboot;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

	@Autowired
	private StepBuilderFactory stf;

	@Autowired
	private JobBuilderFactory jbf;

	@Bean
	public Job job() {
		return jbf.get("job1").incrementer(new RunIdIncrementer()).listener(myJobListener()).start(step()).build();
	}

	@Bean
	public Step step() {

		/*
		 * chunk(1) - Specifies the data to be read before invoking writer method
		 * 
		 */

		return stf.get("step1").<String, String>chunk(1).reader(reader()).processor(processor()).writer(writer())
				.build();
	}

	@Bean
	public Reader reader() {
		return new Reader();
	}

	@Bean
	public Writer writer() {
		return new Writer();
	}

	@Bean
	public Processor processor() {
		return new Processor();
	}

	@Bean
	public MyJobListener myJobListener() {
		return new MyJobListener();
	}
}
