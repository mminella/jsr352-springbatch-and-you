package org.springframework.batch.jsr.demo.batchlet;

import javax.batch.api.Batchlet;

public class HelloWorldBatchlet implements Batchlet {

	@Override
	public String process() throws Exception {
		System.err.println("********************************************");
		System.err.println("          Hello, SpringOne!");
		System.err.println("********************************************");
		return null;
	}

	@Override
	public void stop() throws Exception {
	}
}
