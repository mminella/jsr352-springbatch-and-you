package org.springframework.batch.jsr.chunk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;

import org.springframework.batch.jsr.Logger;
import org.springframework.batch.jsr.domain.LogEntry;

/**
 * 216.196.144.189 - - [15/Jul/2003:22:30:24 -0700] "GET /archive/2003/04/29/star_war.shtml HTTP/1.1" 200 81548 "http://tvulive.com/radiou/riot.shtm" "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"
 * @author Michael Minella
 *
 */
public class LogReadingItemReader implements ItemReader {

	@Inject
	@BatchProperty
	private String directoryName;

	private BufferedReader reader;
	private HashSet<String> filesRead;
	private Set<String> filesToRead;
	private String curFile;
	private Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(\\d+/\\w+/\\d+:\\d+:\\d+:\\d+ [+-]\\d+)\\] \"\\w+ ((/[^/ ]*)+)");
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z");

	@Override
	@SuppressWarnings("unchecked")
	public void open(Serializable checkpoint) throws Exception {
		Logger.log("directoryName = " + directoryName);

		if(checkpoint != null) {
			filesRead = (HashSet<String>) checkpoint;
		} else {
			filesRead = new HashSet<String>();
		}

		File[] files = new File(directoryName).listFiles();
		filesToRead = new HashSet<String>();

		for (File curFile : files) {
			filesToRead.add(curFile.getAbsolutePath());
		}

		filesToRead.removeAll(filesRead);
	}

	@Override
	public void close() throws Exception {
		if(reader != null) {
			reader.close();
		}
	}

	@Override
	public Object readItem() throws Exception {
		LogEntry entry = null;

		while(filesToRead.size() > 0) {
			if(reader == null) {
				curFile = filesToRead.iterator().next();
				if(curFile != null) {
					reader = new BufferedReader(new FileReader(new File(curFile)));
				} else {
					return null;
				}
			}

			String line = reader.readLine();

			if(line != null) {
				entry = parseLine(line);
				break;
			} else {
				filesToRead.remove(curFile);
				filesRead.add(curFile);
				reader.close();
				reader = null;
			}
		}

		return entry;
	}

	protected LogEntry parseLine(String line) throws Exception {
		LogEntry entry = new LogEntry();

		Matcher matcher = pattern.matcher(line);
		matcher.find();
		entry.setIpAddress(matcher.group(1));

		String date = matcher.group(2);
		entry.setViewDate(formatter.parse(date));
		entry.setRequestedUrl(matcher.group(3));

		return entry;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return filesRead;
	}
}
