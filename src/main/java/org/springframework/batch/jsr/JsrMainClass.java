package org.springframework.batch.jsr;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;

public class JsrMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobOperator jobOperator = BatchRuntime.getJobOperator();

		Properties jobParameters = new Properties();
		boolean restart = false;

		for(int i = 1; i < args.length; i++) {
			jobParameters.put(args[i].substring(0, args[i].indexOf('=')), args[1].substring(args[i].indexOf('=') + 1));
		}

		long executionId = -1;

		if(restart) {
			executionId = jobOperator.restart(Long.parseLong(args[0]), jobParameters);
		} else {
			executionId = jobOperator.start(args[0], jobParameters);
		}

		System.err.println(">>> The execution id of the job run was : " + executionId);
	}
}
