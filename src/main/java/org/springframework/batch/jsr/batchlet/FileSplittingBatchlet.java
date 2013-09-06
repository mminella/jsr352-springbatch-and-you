package org.springframework.batch.jsr.batchlet;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.inject.Inject;

public class FileSplittingBatchlet implements Batchlet {

	@Inject
	@BatchProperty
	private String fileName;

	@Inject
	@BatchProperty
	private String lines;

	private int lineCount;
	private static String fileSeperator = System.getProperty("file.separator");

	@Override
	public String process() throws Exception {
		init();
		splitFile();

		return null;
	}

	@Override
	public void stop() throws Exception {
	}

	private void init() {
		if(fileName == null || fileName.trim().length() <= 0) {
			throw new RuntimeException("File name is required");
		}

		if(lines == null || lines.trim().length() <= 0) {
			throw new RuntimeException("Number of lines is required");
		}

		lineCount = Integer.parseInt(lines);
	}

	private void splitFile() throws Exception {

		String outputDirectory = fileName.substring(0, fileName.lastIndexOf(fileSeperator)) + fileSeperator + "out" + fileSeperator;

		File output = new File(outputDirectory);

		if(!output.exists()) {
			output.mkdir();
		}

		final String command = String.format("split -a 5 -l %d %s %s", lineCount, fileName, outputDirectory);

		FutureTask<Integer> systemCommandTask = new FutureTask<Integer>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				String parentPath = new File(fileName).getParent();
				Process process = Runtime.getRuntime().exec(command, new String [] {}, new File(parentPath));
				return process.waitFor();
			}

		});

		long t0 = System.currentTimeMillis();

		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(systemCommandTask);

		while (true) {
			Thread.sleep(500l);//moved to the end of the logic
			if (systemCommandTask.isDone()) {
				executor.shutdown();
				break;
			}
			else if (System.currentTimeMillis() - t0 > 15000) {
				executor.shutdownNow();
				throw new RuntimeException("Splitting the file took too long");
			}
		}
	}
}
