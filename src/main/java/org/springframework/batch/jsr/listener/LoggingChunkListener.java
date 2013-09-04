package org.springframework.batch.jsr.listener;

import javax.batch.api.chunk.listener.ChunkListener;

public class LoggingChunkListener implements ChunkListener {

	@Override
	public void beforeChunk() throws Exception {
		System.err.println("Before Chunk");
	}

	@Override
	public void onError(Exception ex) throws Exception {
		System.err.println("The error that occured durring the chunk was: " + ex.getMessage());
	}

	@Override
	public void afterChunk() throws Exception {
		System.err.println("After Chunk");
	}
}
