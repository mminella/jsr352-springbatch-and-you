package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.jsr.Logger;

public class LoggingStepListener implements StepListener, StepExecutionListener {

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

	@Override
	public void beforeStep(StepExecution stepExecution) {
		Logger.log("    beforeStep "  + stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		Logger.log("    afterStep " + stepExecution.getFailureExceptions().size());
		return stepExecution.getExitStatus();
	}
}
