package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.JobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

public class LoggingJobListener implements JobListener {

	@Inject
	public JobContext jobContext;

	@Override
	public void beforeJob() throws Exception {
		System.err.println("About to start job " + jobContext.getJobName());
	}

	@Override
	public void afterJob() throws Exception {
		System.err.println(jobContext.getJobName() + " has completed.");
	}
}
