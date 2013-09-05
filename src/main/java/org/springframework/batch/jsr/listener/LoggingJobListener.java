package org.springframework.batch.jsr.listener;

import javax.batch.api.listener.JobListener;

import org.springframework.batch.jsr.Logger;

public class LoggingJobListener implements JobListener {

	private String jobName;

	public void setJobName(String name) {
		this.jobName = name;
	}

	@Override
	public void beforeJob() throws Exception {
		Logger.log(jobName + " -- STARTING");
	}

	@Override
	public void afterJob() throws Exception {
		Logger.log(jobName + " -- FINSHED");
	}
}
