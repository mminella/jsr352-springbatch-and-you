package org.springframework.batch.jsr.listener;

import java.util.List;

import javax.batch.api.chunk.listener.ItemWriteListener;

import org.springframework.batch.jsr.Logger;

public class LoggingItemWriteListener implements ItemWriteListener {

	@Override
	public void beforeWrite(List<Object> items) throws Exception {
		Logger.log("            Write -- BEFORE: " + items.size() + " items");
	}

	@Override
	public void afterWrite(List<Object> items) throws Exception {
		Logger.log("            Write -- AFTER: " + items.size() + " items");
	}

	@Override
	public void onWriteError(List<Object> items, Exception ex) throws Exception {
		Logger.log("            Write -- ERROR: " + items.size() + " items and " + ex.getMessage());
	}
}
