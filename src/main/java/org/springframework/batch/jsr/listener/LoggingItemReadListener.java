package org.springframework.batch.jsr.listener;

import javax.batch.api.chunk.listener.ItemReadListener;

import org.springframework.batch.jsr.Logger;

public class LoggingItemReadListener implements ItemReadListener {

	@Override
	public void beforeRead() throws Exception {
		Logger.log("            Reader -- BEFORE");
	}

	@Override
	public void afterRead(Object item) throws Exception {
		Logger.log("            Reader -- AFTER: " + item);
	}

	@Override
	public void onReadError(Exception ex) throws Exception {
		Logger.log("            Reader -- ERROR: " + ex.getMessage());
	}
}
