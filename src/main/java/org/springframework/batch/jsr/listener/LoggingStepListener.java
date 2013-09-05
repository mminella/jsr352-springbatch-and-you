package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

import org.springframework.batch.jsr.Logger;

public class LoggingStepListener implements StepListener {

	@Inject
	public StepContext stepContext;

	@Override
	public void beforeStep() throws Exception {
		Logger.log("    " + stepContext.getStepName() + " -- STARTING");
	}

	@Override
	public void afterStep() throws Exception {
		Logger.log("    " + stepContext.getStepName() + " -- FINISHED");
	}
}
