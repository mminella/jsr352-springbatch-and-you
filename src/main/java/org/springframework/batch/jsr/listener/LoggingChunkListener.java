package org.springframework.batch.jsr.listener;

import javax.batch.api.chunk.listener.ChunkListener;

import org.springframework.batch.jsr.Logger;

public class LoggingChunkListener implements ChunkListener {

	@Override
	public void beforeChunk() throws Exception {
		Logger.log("        Chunk -- STARTING");
	}

	@Override
	public void onError(Exception ex) throws Exception {
		Logger.log("        Chunk -- An error occured: " + ex.getMessage());
	}

	@Override
	public void afterChunk() throws Exception {
		Logger.log("        Chunk -- FINISHED");
	}
}
