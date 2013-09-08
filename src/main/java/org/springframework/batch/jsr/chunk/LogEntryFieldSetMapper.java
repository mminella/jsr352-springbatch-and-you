package org.springframework.batch.jsr.chunk;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.jsr.domain.LogEntry;
import org.springframework.validation.BindException;

public class LogEntryFieldSetMapper implements FieldSetMapper<LogEntry> {

	@Override
	public LogEntry mapFieldSet(FieldSet fieldSet) throws BindException {
		LogEntry entry = new LogEntry();

		entry.setIpAddress(fieldSet.readString(0));
		entry.setViewDate(fieldSet.readDate(1, "dd/MMM/yyyy:kk:mm:ss Z"));
		entry.setRequestedUrl(fieldSet.readString(2));

		return entry;
	}
}
