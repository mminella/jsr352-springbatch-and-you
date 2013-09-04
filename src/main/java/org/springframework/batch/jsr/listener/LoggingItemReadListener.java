package org.springframework.batch.jsr.listener;

import javax.batch.api.chunk.listener.ItemReadListener;

public class LoggingItemReadListener implements ItemReadListener {

	@Override
	public void beforeRead() throws Exception {
		System.err.println("Before read");
	}

	@Override
	public void afterRead(Object item) throws Exception {
		System.err.println("After reading " + item);
	}

	@Override
	public void onReadError(Exception ex) throws Exception {
		System.err.println("A read error occured: " + ex.getMessage());
	}
}
