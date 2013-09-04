package org.springframework.batch.jsr.listener;

import java.util.List;

import javax.batch.api.chunk.listener.ItemWriteListener;

public class LoggingItemWriteListener implements ItemWriteListener {

	@Override
	public void beforeWrite(List<Object> items) throws Exception {
		System.err.println("Before writing " + items.size() + " items");
	}

	@Override
	public void afterWrite(List<Object> items) throws Exception {
		System.err.println(items.size() + " were written");
	}

	@Override
	public void onWriteError(List<Object> items, Exception ex) throws Exception {
		System.err.println("An error occured while writing: " + ex.getMessage());
	}
}
