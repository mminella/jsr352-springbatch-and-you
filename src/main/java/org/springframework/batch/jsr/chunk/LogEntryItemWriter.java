package org.springframework.batch.jsr.chunk;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.sql.DataSource;

import org.springframework.batch.jsr.domain.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;

public class LogEntryItemWriter implements ItemWriter {

	@Autowired
	public DataSource dataSource;
	private Connection connection;
	private PreparedStatement statement;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		connection = dataSource.getConnection();
		statement = connection.prepareStatement("INSERT INTO logEntry (ip_address, requested_url, country_code, view_date) VALUES(?, ?, ?, ?)");
	}

	@Override
	public void close() throws Exception {
		statement.close();
		connection.close();
	}

	@Override
	public void writeItems(List<Object> items) throws Exception {
		if(items != null && !items.isEmpty()) {
			for (Object item : items) {
				LogEntry entry = (LogEntry) item;

				statement.setString(1, entry.getIpAddress());
				statement.setString(2, entry.getRequestedUrl());
				statement.setString(3, entry.getCountryCode());
				statement.setDate(4, new Date(entry.getViewDate().getTime()));

				statement.addBatch();
			}

			statement.executeBatch();
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
