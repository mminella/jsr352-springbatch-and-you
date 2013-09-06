package org.springframework.batch.jsr.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {

	private String ipAddress;
	private String requestedUrl;
	private String countryCode;
	private Date viewDate;

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	public String getRequestedUrl() {
		return requestedUrl;
	}
	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		String baseString = ipAddress + "|" + requestedUrl + "|" + new SimpleDateFormat("MM/dd/yyyy hh:mm:ss Z").format(viewDate);

		if(countryCode != null) {
			baseString = baseString + "|" + countryCode;
		}

		return baseString;
	}
}
