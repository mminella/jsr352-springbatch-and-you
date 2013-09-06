package org.springframework.batch.jsr.chunk;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;

public class WritingItemWriter implements ItemWriter {

	@Override
	public void open(Serializable checkpoint) throws Exception {
	}

	@Override
	public void close() throws Exception {
	}

	@Override
	public void writeItems(List<Object> items) throws Exception {
		for (Object object : items) {
			System.out.println(object);
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
