package org.springframework.batch.jsr.listener;

import java.util.List;

import javax.batch.api.chunk.listener.RetryProcessListener;
import javax.batch.api.chunk.listener.RetryReadListener;
import javax.batch.api.chunk.listener.RetryWriteListener;

import org.springframework.batch.jsr.Logger;

public class LoggingRetryListener implements RetryProcessListener,
RetryReadListener, RetryWriteListener {

	@Override
	public void onRetryWriteException(List<Object> items, Exception ex)
			throws Exception {
		Logger.log("            Retrying Write -- " + items.size() + " because of " + ex.getMessage());
	}

	@Override
	public void onRetryReadException(Exception ex) throws Exception {
		Logger.log("            Retrying Read -- " + ex.getMessage());
	}

	@Override
	public void onRetryProcessException(Object item, Exception ex)
			throws Exception {
		Logger.log("            Retrying Process -- " + item + " because of " + ex.getMessage());
	}
}
