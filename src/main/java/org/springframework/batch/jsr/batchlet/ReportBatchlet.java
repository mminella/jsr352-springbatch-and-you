package org.springframework.batch.jsr.batchlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.batch.api.Batchlet;
import javax.sql.DataSource;

import org.springframework.batch.jsr.chunk.CountryCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class ReportBatchlet implements Batchlet {

	@Autowired
	public DataSource dataSource;
	private Configuration cfg;

	private static final String QUERY = "select country_code, count(*) as qty from logentry group by country_code order by qty";

	@Override
	public String process() throws Exception {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(QUERY);

		ResultSet rs = statement.executeQuery();

		List<Map<String, Object>> countries = new ArrayList<Map<String,Object>>();
		long min = Long.MAX_VALUE;
		long max = 0l;

		while(rs.next()) {
			String countryCode = rs.getString(1);
			long count = rs.getLong(2);

			Set<String> codes = CountryCodeMapper.mapCountryCode(countryCode);

			if(codes != null) {
				for (String code : codes) {
					Map<String, Object> country = new HashMap<String, Object>();
					country.put("countryCode", code);
					country.put("count", count);

					countries.add(country);
				}
			}

			if(count > max) {
				max = count;
			}

			if(count < min) {
				min = count;
			}

		}

		scaleValues(countries, min, max);

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("countries", countries);

		initFreemarker();

		Template template = cfg.getTemplate("index.ftl");
		File outputDirectory = new File("/tmp/jsr_temp/output/");
		if(!outputDirectory.exists()) {
			outputDirectory.mkdir();
		}

		Writer outWriter = new OutputStreamWriter(new FileOutputStream("/tmp/jsr_temp/output/index.html"));
		template.process(modelMap, outWriter);

		return null;
	}

	private void scaleValues(List<Map<String, Object>> countries, long min,
			long max) {

		for (Map<String, Object> map : countries) {
			map.put("scale", (((10 - 1) * (((Long)map.get("count")).longValue() - min))/(max - min)) + 1l);
		}
	}

	private void initFreemarker() throws Exception {
		cfg = new Configuration();

		cfg.setClassForTemplateLoading(ReportBatchlet.class, "/template/");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	@Override
	public void stop() throws Exception {
	}
}
