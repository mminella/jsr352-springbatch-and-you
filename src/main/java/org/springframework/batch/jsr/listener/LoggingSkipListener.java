package org.springframework.batch.jsr.listener;

import java.util.List;

import javax.batch.api.chunk.listener.SkipProcessListener;
import javax.batch.api.chunk.listener.SkipReadListener;
import javax.batch.api.chunk.listener.SkipWriteListener;

public class LoggingSkipListener implements SkipProcessListener,
SkipReadListener, SkipWriteListener {

	@Override
	public void onSkipWriteItem(List<Object> items, Exception ex)
			throws Exception {
		System.err.println("A skipped error occured while processing a write:" +ex.getMessage());
	}

	@Override
	public void onSkipReadItem(Exception ex) throws Exception {
		System.err.println("A skipped error occured during a read: " + ex.getMessage());
	}

	@Override
	public void onSkipProcessItem(Object item, Exception ex) throws Exception {
		System.err.println("A skipped error occured during the processing of " + item + ":  " + ex.getMessage());
	}
}
