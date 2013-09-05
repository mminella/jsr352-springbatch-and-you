package org.springframework.batch.jsr.batchlet;

import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.inject.Inject;

import org.springframework.batch.jsr.Logger;

public class EchoBatchlet implements Batchlet {

	@Inject
	@BatchProperty
	public String message;

	@Override
	public String process() throws Exception {
		for(int i = 0; i < 1000; i++) {
			Logger.log(message);
		}
		return null;
	}

	@Override
	public void stop() throws Exception {
	}
}
