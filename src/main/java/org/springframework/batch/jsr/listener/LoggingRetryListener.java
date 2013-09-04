package org.springframework.batch.jsr.listener;

import java.util.List;

import javax.batch.api.chunk.listener.RetryProcessListener;
import javax.batch.api.chunk.listener.RetryReadListener;
import javax.batch.api.chunk.listener.RetryWriteListener;

public class LoggingRetryListener implements RetryProcessListener,
RetryReadListener, RetryWriteListener {

	@Override
	public void onRetryWriteException(List<Object> items, Exception ex)
			throws Exception {
		System.err.println("Write was retried: " + ex.getMessage());
	}

	@Override
	public void onRetryReadException(Exception ex) throws Exception {
		System.err.println("Read was retried: " + ex.getMessage());
	}

	@Override
	public void onRetryProcessException(Object item, Exception ex)
			throws Exception {
		System.err.println("Process was retried for item " + item + " : " + ex.getMessage());
	}
}
