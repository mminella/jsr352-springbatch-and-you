package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

public class LoggingStepListener implements StepListener {

	@Inject
	public StepContext stepContext;

	@Inject
	public JobContext jobContext;

	@Override
	public void beforeStep() throws Exception {
		System.err.println("About to execute step " + stepContext.getStepName() + " in job " + jobContext.getJobName());
	}

	@Override
	public void afterStep() throws Exception {
		System.err.println("Step " + stepContext.getStepName() + " in job " + jobContext.getJobName() + " has finished");
	}
}
