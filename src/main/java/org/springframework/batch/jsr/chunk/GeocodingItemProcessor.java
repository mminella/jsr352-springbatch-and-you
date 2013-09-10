package org.springframework.batch.jsr.chunk;

import java.io.File;
import java.net.InetAddress;

import javax.batch.api.chunk.ItemProcessor;

import org.springframework.batch.jsr.Logger;
import org.springframework.batch.jsr.domain.LogEntry;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;

public class GeocodingItemProcessor implements ItemProcessor {

	//	private static final String ORIGINAL_FILE_NAME = "ghyslain_razaa.wmv";
	//	private static final String NEW_FILE_NAME = "Star_Wars_Kid.wmv";
	private DatabaseReader reader;

	public GeocodingItemProcessor() throws Exception {
		reader = new DatabaseReader(new File("/usr/local/share/GeoIP/GeoLite2-Country.mmdb"));
	}

	@Override
	public Object processItem(Object item) throws Exception {
		LogEntry entry = (LogEntry) item;

		try {
			entry.setCountryCode(reader.country(InetAddress.getByName(entry.getIpAddress())).getCountry().getIsoCode());
			Logger.log(entry.toString());
		} catch (AddressNotFoundException anfe) {
			return null;
		}

		//		String url = entry.getRequestedUrl();
		//		if(url.indexOf(ORIGINAL_FILE_NAME) >= 0 || url.indexOf(NEW_FILE_NAME) >= 0) {
		return entry;
		//		} else {
		//			return null;
		//		}
	}
}
