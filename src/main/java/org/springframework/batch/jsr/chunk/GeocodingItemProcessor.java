package org.springframework.batch.jsr.chunk;

import java.io.File;
import java.net.InetAddress;

import javax.batch.api.chunk.ItemProcessor;

import org.springframework.batch.jsr.Logger;
import org.springframework.batch.jsr.domain.LogEntry;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;

public class GeocodingItemProcessor implements ItemProcessor {

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

		return entry;
	}
}
