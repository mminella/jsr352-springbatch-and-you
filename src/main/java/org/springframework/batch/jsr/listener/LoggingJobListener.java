package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.JobListener;

public class LoggingJobListener implements JobListener {

	private String jobName;

	public void setJobName(String name) {
		this.jobName = name;
	}

	@Override
	public void beforeJob() throws Exception {
		System.err.println("About to start job " + jobName);
	}

	@Override
	public void afterJob() throws Exception {
		System.err.println(jobName + " has completed.");
	}
}
