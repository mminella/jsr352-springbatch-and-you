package org.springframework.batch.jsr.listener;

import javax.batch.api.chunk.listener.ItemProcessListener;

public class LoggingItemProcessListener implements ItemProcessListener {

	@Override
	public void beforeProcess(Object item) throws Exception {
		System.err.println("Before processing " + item);
	}

	@Override
	public void afterProcess(Object item, Object result) throws Exception {
		System.err.println("After processing " + item + " the result was " + result);
	}

	@Override
	public void onProcessError(Object item, Exception ex) throws Exception {
		System.err.println("An error occured while processing " + item + ":" + ex.getMessage());
	}
}
