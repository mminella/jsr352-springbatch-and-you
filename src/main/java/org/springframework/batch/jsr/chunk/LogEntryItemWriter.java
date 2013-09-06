package org.springframework.batch.jsr.chunk;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class LogEntryItemWriter implements ItemWriter {

	@Autowired
	public DataSource dataSource;

	@Override
	public void open(Serializable checkpoint) throws Exception {
	}

	@Override
	public void close() throws Exception {
	}

	@Override
	public void writeItems(List<Object> items) throws Exception {
		if(items != null && !items.isEmpty()) {
			for (Object entry : items) {

			}
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
