package org.springframework.batch.jsr.demo.batchlet;

import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.inject.Inject;

public class HelloWorldBatchlet implements Batchlet {

	@Inject
	@BatchProperty
	private String greeting = "Hello";

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public String process() throws Exception {
		System.err.println("********************************************");
		System.err.println("          " + greeting + ", SpringOne!");
		System.err.println("********************************************");
		return null;
	}

	@Override
	public void stop() throws Exception {
	}
}
